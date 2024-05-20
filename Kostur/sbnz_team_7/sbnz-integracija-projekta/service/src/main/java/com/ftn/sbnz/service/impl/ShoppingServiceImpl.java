package com.ftn.sbnz.service.impl;

import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.repository.ShoppingRepository;
import com.ftn.sbnz.service.ShoppingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Override
    public Shopping save(String productId, ObjectId userId){

        ObjectId objectId;
        try {
            objectId = new ObjectId(productId);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid productId format");
        }
        Shopping shopping = new Shopping(objectId,userId, LocalDateTime.now());
        return shoppingRepository.save(shopping);


    }

    @Override
    public List<Shopping> findAll() {
        return shoppingRepository.findAll();
    }
}
