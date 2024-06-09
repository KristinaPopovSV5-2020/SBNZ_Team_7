package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.product.FeedbackDTO;
import com.ftn.sbnz.kafka.EventHandler;
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
    private final EventHandler eventHandler;


    private final KieContainer kieContainer;

    public FeedbackServiceImpl(UserRepository userRepository, ShoppingService shoppingService, FeedbackRepository feedbackRepository, EventHandler eventHandler, KieContainer kieContainer) {
        this.userRepository = userRepository;
        this.shoppingService = shoppingService;
        this.feedbackRepository = feedbackRepository;
        this.eventHandler = eventHandler;
        this.kieContainer = kieContainer;
    }

    @Override
    public Feedback save(FeedbackDTO feedbackDTO, ObjectId userId) {
        Feedback feedback = ObjectMapper.feedbackToEntity(feedbackDTO, userId);

        Feedback feedbackEvent = eventHandler.processFeedbackEvent(feedback);

        if (feedbackEvent == null){
            return null;
        }else{
            return feedbackRepository.save(feedbackEvent);
        }
    }

    @Override
    public void deleteAll() {
        feedbackRepository.deleteAll();
    }
}
