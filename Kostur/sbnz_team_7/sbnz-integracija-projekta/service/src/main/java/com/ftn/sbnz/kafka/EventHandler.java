package com.ftn.sbnz.kafka;

import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.RatingHelper;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.repository.DiscountRepository;
import com.ftn.sbnz.repository.FeedbackRepository;
import com.ftn.sbnz.repository.ProductRepository;
import com.ftn.sbnz.repository.ShoppingRepository;
import com.ftn.sbnz.service.DiscountService;
import com.ftn.sbnz.service.ProductService;
import com.ftn.sbnz.service.implementation.UserServiceImpl;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class EventHandler {


    private final KieContainer kieContainer;
    private final UserServiceImpl userService;

    private final ShoppingRepository shoppingRepository;
    private final FeedbackRepository feedbackRepository;
    private final ProductRepository productRepository;

    private final DiscountService discountService;

    private final DiscountRepository discountRepository;

    private final ProductService productService;

    @Autowired
    public EventHandler(KieContainer kieContainer, UserServiceImpl userService, ShoppingRepository shoppingRepository, FeedbackRepository feedbackRepository, ProductRepository productRepository, DiscountService discountService, DiscountRepository discountRepository, ProductService productService) {
        this.kieContainer = kieContainer;
        this.userService = userService;
        this.shoppingRepository = shoppingRepository;
        this.feedbackRepository = feedbackRepository;
        this.productRepository = productRepository;
        this.discountService = discountService;
        this.discountRepository = discountRepository;
        this.productService = productService;
    }

    public Shopping processShoppingEvent(Shopping shoppingEvent) {
        KieSession kieSession = kieContainer.newKieSession("cepBonusKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();
        List<String> gifts = Arrays.asList(
                "Sunscreen Tester",
                "Hydration Serum Tester",
                "Anti-Ageing Cream Tester",
                "Exfoliating Scrub Tester",
                "Brightening Mask Tester"
        );

        List<Product> matchingProductList = new ArrayList<>();
        kieSession.setGlobal("matchingProductList", matchingProductList);
        kieSession.setGlobal("discountService", discountService);
        kieSession.setGlobal("userService", userService);
        kieSession.setGlobal("gifts", gifts);
        insertDataIntoSession(kieSession, shoppingEvent.getUserId(), clock, shoppingEvent);

        kieSession.getAgenda().getAgendaGroup("collection-rules").setFocus();
        int collectionRulesFired = kieSession.fireAllRules();
        System.out.println("Number of collection rules fired: " + collectionRulesFired);

        kieSession.getAgenda().getAgendaGroup("discount-rules").setFocus();
        int discountRulesFired = kieSession.fireAllRules();
        System.out.println("Number of discount rules fired: " + discountRulesFired);


        Optional<Product> productOpt = productRepository.findById(shoppingEvent.getProductId());
        if (productOpt.isPresent()) {
            shoppingEvent.setValue(productOpt.get().getPrice());
        } else {
            throw new BadRequestException("Product not found");
        }

        kieSession.dispose();

        return shoppingEvent;
    }

    public boolean processCheckFeedbackReport(){
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        try {
            SessionPseudoClock clock = kieSession.getSessionClock();

            List<Product> allProducts = productService.findAll();
            allProducts.forEach(kieSession::insert);

            RatingHelper ratingHelper = insertDataCepReportFeedbackIntoSession(kieSession,clock);
            kieSession.getAgenda().getAgendaGroup("report-rules").setFocus();
            int reportRulesFired = kieSession.fireAllRules();
            System.out.println("Number of report rules fired: " + reportRulesFired);


            Boolean canGenerateReport = ratingHelper.getCanGenerateReport();
            System.out.println("Can generate report: " + canGenerateReport);
            if (canGenerateReport){
                return true;
            }else {
                return false;
            }
        }finally {
            kieSession.dispose();
        }

    }

    public Feedback processFeedbackEvent(Feedback feedbackEvent){
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        try {
            SessionPseudoClock clock = kieSession.getSessionClock();
            List<Product> allProducts = productService.findAll();
            allProducts.forEach(kieSession::insert);
            kieSession.setGlobal("productService", productService);
            RatingHelper ratingHelper = insertDataCepFeedbackIntoSession(kieSession,feedbackEvent.getUserId(),clock,feedbackEvent);
            kieSession.getAgenda().getAgendaGroup("feedback-rules").setFocus();
            int feedbackRulesFired = kieSession.fireAllRules();
            System.out.println("Number of feedback rules fired: " + feedbackRulesFired);

            Boolean canRate = ratingHelper.getCanRate();
            System.out.println("Can rate: " + canRate);

            if (canRate) {
                return feedbackEvent;
            } else {
                return null;
            }
        }finally {
            kieSession.dispose();
        }
    }


    private void insertDataIntoSession(KieSession kieSession, ObjectId userId, SessionPseudoClock clock, Shopping shoppingEvent) {
        kieSession.insert(userService.findOne(userId));
        insertFeedbacksIntoSession(kieSession, userId, clock);
        insertShoppingsIntoSession(kieSession, userId, clock);
        insertDiscountsIntoSession(kieSession, userId);
        insertProductsIntoSession(kieSession);
        insertShoppingEventIntoSession(kieSession, shoppingEvent, clock);


    }

    public RatingHelper insertDataCepFeedbackIntoSession(KieSession kieSession, ObjectId userId, SessionPseudoClock clock, Feedback feedbackEvent){
        kieSession.insert(userService.findOne(userId));
        insertFeedbackEventIntoSession(kieSession,feedbackEvent,clock);
        insertAllFeedbacksIntoSession(kieSession, clock);
        insertShoppingsIntoSession(kieSession, userId, clock);
        RatingHelper ratingHelper = new RatingHelper(feedbackEvent.getProductId());
        kieSession.insert(ratingHelper);
        return ratingHelper;

    }

    public RatingHelper insertDataCepReportFeedbackIntoSession(KieSession kieSession, SessionPseudoClock clock){
        insertAllFeedbacksIntoSession(kieSession, clock);
        insertAllShoppingsIntoSession(kieSession, clock);
        RatingHelper ratingHelper = new RatingHelper(null);
        kieSession.insert(ratingHelper);
        return ratingHelper;

    }

    private void insertShoppingEventIntoSession(KieSession kieSession, Shopping shoppingEvent, SessionPseudoClock clock) {
        long shoppingTimeDiff = shoppingEvent.getDateTime().getTime() - clock.getCurrentTime();
        clock.advanceTime(shoppingTimeDiff, TimeUnit.MILLISECONDS);
        shoppingEvent.setIsNew(true); // Postavljam novu kupovinu na true
        kieSession.insert(shoppingEvent);
        System.out.println("Inserted shopping event at: " + shoppingEvent.getDateTime());
    }

    private void insertFeedbackEventIntoSession(KieSession kieSession, Feedback feedbackEvent, SessionPseudoClock clock){
        long feedbackTimeDiff = feedbackEvent.getDateTime().getTime() - clock.getCurrentTime();
        clock.advanceTime(feedbackTimeDiff, TimeUnit.MILLISECONDS);
        feedbackEvent.setNew(true); //Postavljam novi utisak na true
        kieSession.insert(feedbackEvent);
        System.out.println("Inserted feedback event at: " + feedbackEvent.getDateTime());
    }


    private void insertFeedbacksIntoSession(KieSession kieSession, ObjectId userId, SessionPseudoClock clock) {
        List<Feedback> feedbacks = getFeedbacksByUserId(userId);
        feedbacks.forEach(feedback -> {
            long timeDiff = feedback.getDateTime().getTime() - clock.getCurrentTime();
            feedback.setNew(false);// Postavljam stare utiske na false
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            kieSession.insert(feedback);
        });
    }

    public void insertAllFeedbacksIntoSession(KieSession kieSession, SessionPseudoClock clock) {
        List<Feedback> feedbacks = getAllFeedbacks();
        feedbacks.forEach(feedback -> {
            long timeDiff = feedback.getDateTime().getTime() - clock.getCurrentTime();
            feedback.setNew(false);// Postavljam stare utiske na false
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            kieSession.insert(feedback);
        });
    }

    public void insertShoppingsIntoSession(KieSession kieSession, ObjectId userId, SessionPseudoClock clock) {
        List<Shopping> shoppings = getShoppingsByUserId(userId);
        shoppings.forEach(shopping -> {
            long timeDiff = shopping.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            shopping.setIsNew(false); // Postavljam stare kupovine na false
            kieSession.insert(shopping);
        });
    }

    private void insertAllShoppingsIntoSession(KieSession kieSession,SessionPseudoClock clock) {
        List<Shopping> shoppings = getAllShoppings();
        shoppings.forEach(shopping -> {
            long timeDiff = shopping.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            shopping.setIsNew(false); // Postavljam stare kupovine na false
            kieSession.insert(shopping);
        });
    }



    private void insertProductsIntoSession(KieSession kieSession) {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            kieSession.insert(product);
        });
    }


    private void insertDiscountsIntoSession(KieSession kieSession, ObjectId userId) {
        List<Discount> discounts = discountRepository.findByUserId(userId);
        discounts.forEach(discount -> {
            kieSession.insert(discount);
            System.out.println("insertovan: " + discount.getValue());
        });
    }

    private List<Feedback> getFeedbacksByUserId(ObjectId userId) {
        return feedbackRepository.findByUserId(userId);
    }

    private List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    private List<Shopping> getShoppingsByUserId(ObjectId userId) {
        return shoppingRepository.findByUserId(userId);
    }

    private List<Shopping> getAllShoppings() {
        return shoppingRepository.findAll();
    }
}
