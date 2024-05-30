package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.product.FeedbackDTO;
import com.ftn.sbnz.helper.DroolsHelper;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.RatingHelper;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.FeedbackRepository;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.service.FeedbackService;
import com.ftn.sbnz.service.ShoppingService;
import com.ftn.sbnz.util.ObjectMapper;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    private final UserRepository userRepository;


    private final ShoppingService shoppingService;


    private final FeedbackRepository feedbackRepository;


    private final KieContainer kieContainer;

    public FeedbackServiceImpl(UserRepository userRepository, ShoppingService shoppingService, FeedbackRepository feedbackRepository, KieContainer kieContainer) {
        this.userRepository = userRepository;
        this.shoppingService = shoppingService;
        this.feedbackRepository = feedbackRepository;
        this.kieContainer = kieContainer;
    }

    @Override
    public Feedback save(FeedbackDTO feedbackDTO, ObjectId userId) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            List<Shopping> allPurchases = shoppingService.findAll();
            List<Feedback> allFeedback = feedbackRepository.findAll();
            kieSession.setGlobal("droolsHelper", new DroolsHelper());
            RatingHelper ratingHelper = new RatingHelper();
            kieSession.insert(ratingHelper);
            kieSession.insert(user);
            allPurchases.forEach(kieSession::insert);
            allFeedback.forEach(kieSession::insert);
            kieSession.insert(feedbackDTO);
            int fired = kieSession.fireAllRules();
            System.out.println("ispaljeno" + fired);  //  debugging :)

            Boolean canRate = ratingHelper.getCanRate();
            System.out.println("Can rate: " + canRate);

            if (canRate) {
                Feedback feedback = ObjectMapper.feedbackToEntity(feedbackDTO, userId);
                return feedbackRepository.save(feedback);
            } else {
                return null;
            }

        } finally {
            kieSession.dispose();
        }
    }

    @Override
    public void deleteAll() {
        feedbackRepository.deleteAll();
    }
}
