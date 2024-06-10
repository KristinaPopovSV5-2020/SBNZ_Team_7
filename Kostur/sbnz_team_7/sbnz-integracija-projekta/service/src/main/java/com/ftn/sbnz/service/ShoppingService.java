package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.shoppings.ShoppingUserDTO;
import com.ftn.sbnz.model.models.products.Shopping;
import org.bson.types.ObjectId;

import java.util.List;


public interface ShoppingService {

    Shopping save(String productId, ObjectId userId, String discountId);

    List<Shopping> findAll();

    List<ShoppingUserDTO> findShoppingsByUser(ObjectId userId);

    void deleteAll();
}
