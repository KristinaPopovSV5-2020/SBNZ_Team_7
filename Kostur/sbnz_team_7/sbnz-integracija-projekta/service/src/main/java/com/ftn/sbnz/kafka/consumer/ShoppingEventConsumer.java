package com.ftn.sbnz.kafka.consumer;

import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.products.Shopping;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingEventConsumer.class);

    private final KieContainer kieContainer;

    private KieSession kieSession;

    @Autowired
    public ShoppingEventConsumer(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @PostConstruct
    public void initializeSession() {
        this.kieSession = kieContainer.newKieSession("cepBonusKsession");
    }

    @PreDestroy
    public void disposeSession() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

    @KafkaListener(topics = "shopping_events", groupId = "cep_group")
    public void listen(ConsumerRecord<String, Shopping> record) {
        Shopping shoppingEvent = record.value();
        logger.info("Received shopping event: {}", shoppingEvent);
        processShoppingEvent(shoppingEvent);

    }

    private void processShoppingEvent(Shopping shoppingEvent) {
        List<Feedback> feedbacks = getFeedbacksByUserId(shoppingEvent.getUserId(), shoppingEvent.getProductId());
        kieSession.insert(shoppingEvent);
        feedbacks.forEach(feedback -> {
            kieSession.insert(feedback);
            logger.info("Inserted feedback: {}", feedback);
        });

        System.out.println(kieSession.getIdentifier());
        int ana = kieSession.fireAllRules();
        System.out.println(ana);
        logger.info("Processed shopping event: {}", shoppingEvent);

    }

    // Ova metoda je samo primer i treba da je implementiraš prema svojim potrebama
    private List<Feedback> getFeedbacksByUserId(ObjectId userId, ObjectId productId) {
        // Fetch feedbacks from the database
        return List.of(
                new Feedback(new ObjectId(), productId, userId, 10, new Date(2024, 5, 24)),
                new Feedback(new ObjectId(), productId, userId, 10, new Date(2024, 5, 23)),
                new Feedback(new ObjectId(), productId, userId, 10, new Date(2024, 5, 23))
        );
    }
}