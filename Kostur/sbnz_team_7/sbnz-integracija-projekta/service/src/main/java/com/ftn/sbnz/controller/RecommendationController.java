package com.ftn.sbnz.controller;

import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.service.RecommendationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/api/recommendations",produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;


    // TODO: ovdje cemo vjr izvlaciti ulogovanog user-a
    @GetMapping("/products")
    public ResponseEntity<List<Product>> recommendProducts(@RequestParam String userId) {
        List<Product> recommendedProducts = recommendationService.recommendProductsForUser(new ObjectId(userId));
        return ResponseEntity.ok(recommendedProducts);
    }
}
