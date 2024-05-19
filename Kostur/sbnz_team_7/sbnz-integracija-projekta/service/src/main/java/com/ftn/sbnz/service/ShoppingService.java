package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.products.Shopping;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingService {

    Shopping save(String productId, ObjectId userId);

    List<Shopping> findAll();
}
