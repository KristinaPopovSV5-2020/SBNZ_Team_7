package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.products.Shopping;
import org.bson.types.ObjectId;

import java.util.List;


public interface ShoppingService {

    Shopping save(String productId, ObjectId userId);

    List<Shopping> findAll();

    void deleteAll();
}
