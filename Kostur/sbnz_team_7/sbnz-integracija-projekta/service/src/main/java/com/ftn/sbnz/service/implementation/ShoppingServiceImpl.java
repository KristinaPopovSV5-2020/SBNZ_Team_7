package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.kafka.EventHandler;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.repository.ShoppingRepository;
import com.ftn.sbnz.service.ShoppingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {


    private final ShoppingRepository shoppingRepository;
//    private final ShoppingEventProducer shoppingEventProducer;

    private final EventHandler eventHandler;

    @Autowired
    public ShoppingServiceImpl(ShoppingRepository shoppingRepository, EventHandler eventHandler) {
        this.shoppingRepository = shoppingRepository;
        this.eventHandler = eventHandler;
    }

    @Override
    public Shopping save(String productId, ObjectId userId) {

        ObjectId objectId;
        try {
            objectId = new ObjectId(productId);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid productId format");
        }
        Shopping shopping = new Shopping(objectId, userId, new Date());

        Shopping savedShopping = shoppingRepository.save(shopping);

        eventHandler.processShoppingEvent(shopping);

        return savedShopping;


    }


    @Override
    public List<Shopping> findAll() {
        return shoppingRepository.findAll();
    }

    @Override
    public void deleteAll() {
        shoppingRepository.deleteAll();
    }
}
