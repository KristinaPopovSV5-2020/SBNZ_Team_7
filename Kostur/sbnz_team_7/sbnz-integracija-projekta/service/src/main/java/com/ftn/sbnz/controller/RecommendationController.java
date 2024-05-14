package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.BudgetDTO;
import com.ftn.sbnz.dto.product.RecommendedDTO;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.service.RecommendationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/recommendations",produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;


    // TODO: ovdje cemo vjr izvlaciti ulogovanog user-a
    @GetMapping("/products")
    public ResponseEntity<List<RecommendedDTO>> recommendProducts(@RequestParam String userId, @RequestBody BudgetDTO budgetDTO) {
        List<RecommendedDTO> recommendedProducts = recommendationService.recommendProductsForUser(new ObjectId(userId), budgetDTO);
        return ResponseEntity.ok(recommendedProducts);
    }

    // TODO: ovdje cemo vjr izvlaciti ulogovanog user-a
    @PostMapping("/products/problems-habits")
    public ResponseEntity<List<RecommendedDTO>> recommendProductsBasedOnSkinProblemsAndHabits(@RequestParam String userId, @RequestBody Map<String, List<String>> requestBody) {
        List<String> skinProblems = requestBody.get("skinProblems");
        List<String> lifestyleHabits = requestBody.get("lifestyleHabits");
        List<RecommendedDTO> recommendedProducts = recommendationService.recommendProductsBasedOnProblemsAndHabitsForUser(new ObjectId(userId), skinProblems, lifestyleHabits);
        return ResponseEntity.ok(recommendedProducts);
    }
}
