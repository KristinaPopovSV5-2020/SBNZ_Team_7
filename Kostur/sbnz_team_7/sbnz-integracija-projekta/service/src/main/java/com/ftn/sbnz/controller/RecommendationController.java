package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.BudgetDTO;
import com.ftn.sbnz.dto.product.RecommendedDTO;
import com.ftn.sbnz.exception.NotFoundException;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.RecommendationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }


    // TODO: ovdje cemo vjr izvlaciti ulogovanog user-a
    @GetMapping("/products")
    public ResponseEntity<List<RecommendedDTO>> recommendProducts(@RequestParam String userId, @RequestBody BudgetDTO budgetDTO) {
        List<RecommendedDTO> recommendedProducts = recommendationService.recommendProductsForUser(new ObjectId(userId), budgetDTO);
        return ResponseEntity.ok(recommendedProducts);
    }


    @PostMapping("/products/problems-habits")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecommendedDTO>> recommendProductsBasedOnSkinProblemsAndHabits(@RequestBody Map<String, List<String>> requestBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }

        List<String> skinProblems = requestBody.get("skinProblems");
        List<String> lifestyleHabits = requestBody.get("lifestyleHabits");
        List<RecommendedDTO> recommendedProducts = recommendationService.recommendProductsBasedOnProblemsAndHabitsForUser(user.getId(), skinProblems, lifestyleHabits);
        return ResponseEntity.ok(recommendedProducts);
    }
}
