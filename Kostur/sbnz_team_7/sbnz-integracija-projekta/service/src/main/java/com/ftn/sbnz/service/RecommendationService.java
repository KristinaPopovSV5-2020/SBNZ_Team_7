package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.products.Product;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {
    List<Product> recommendProductsForUser(ObjectId userId);
}
