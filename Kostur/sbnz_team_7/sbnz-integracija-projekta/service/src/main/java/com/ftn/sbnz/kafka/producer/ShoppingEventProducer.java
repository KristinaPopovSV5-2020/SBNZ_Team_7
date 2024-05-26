package com.ftn.sbnz.kafka.producer;


import com.ftn.sbnz.model.models.products.Shopping;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShoppingEventProducer {

    private final KafkaTemplate<String, Shopping> kafkaTemplate;

    private static final String TOPIC = "shopping_events";

    public ShoppingEventProducer(KafkaTemplate<String, Shopping> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendShoppingEvent(Shopping shoppingEvent) {
        kafkaTemplate.send(TOPIC, shoppingEvent);
    }
}
