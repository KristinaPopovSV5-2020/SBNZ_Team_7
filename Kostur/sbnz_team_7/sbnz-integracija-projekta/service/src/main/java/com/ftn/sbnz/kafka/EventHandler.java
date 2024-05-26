package com.ftn.sbnz.kafka;

import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.repository.FeedbackRepository;
import com.ftn.sbnz.repository.ShoppingRepository;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class EventHandler {
    private KieSession kieSession;

    private final KieContainer kieContainer;

    private final ShoppingRepository shoppingRepository;
    private final FeedbackRepository feedbackRepository;


    @Autowired
    public EventHandler(KieContainer kieContainer, ShoppingRepository shoppingRepository, FeedbackRepository feedbackRepository) {
        this.kieContainer = kieContainer;
        this.shoppingRepository = shoppingRepository;
        this.feedbackRepository = feedbackRepository;
    }

//    @PostConstruct
//    public void initializeSession() {
//        this.kieSession = kieContainer.newKieSession("cepBonusKsession");
//        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
//        config.setOption(ClockTypeOption.get("pseudo"));
//    }
//
//
//    @PreDestroy
//    public void disposeSession() {
//        if (kieSession != null) {
//            kieSession.dispose();
//        }
//    }

    public void processShoppingEvent(Shopping shoppingEvent) {
        KieSession kieSession = kieContainer.newKieSession("cepBonusKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();

        List<Feedback> feedbacks = getFeedbacksByUserId(shoppingEvent.getUserId());
        List<Shopping> shoppings = getShoppingsByUserId(shoppingEvent.getUserId());

        System.out.println("Number of feedbacks: " + feedbacks.size());

        feedbacks.forEach(feedback -> {
            long timeDiff = feedback.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            kieSession.insert(feedback);
            System.out.println("Inserted feedback at: " + feedback.getDateTime());
        });

        shoppings.forEach(shopping -> {
            long timeDiff = shopping.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            shopping.setIsNew(false); // Postavi stare kupovine na false
            kieSession.insert(shopping);
            System.out.println("Inserted shopping at: " + shopping.getDateTime());
        });

        long shoppingTimeDiff = shoppingEvent.getDateTime().getTime() - clock.getCurrentTime();
        clock.advanceTime(shoppingTimeDiff, TimeUnit.MILLISECONDS);
        shoppingEvent.setIsNew(true); // Postavi novu kupovinu na true
        kieSession.insert(shoppingEvent);
        System.out.println("Inserted shopping event at: " + shoppingEvent.getDateTime());

        int numberOfRulesFired = kieSession.fireAllRules();
        System.out.println("Number of rules fired: " + numberOfRulesFired);

        shoppingRepository.save(shoppingEvent);
        kieSession.dispose();
    }


    private List<Feedback> getFeedbacksByUserId(ObjectId userId) {
        return feedbackRepository.findByUserId(userId);
    }

    private List<Shopping> getShoppingsByUserId(ObjectId userId) {
        return shoppingRepository.findByUserId(userId);
    }
}
