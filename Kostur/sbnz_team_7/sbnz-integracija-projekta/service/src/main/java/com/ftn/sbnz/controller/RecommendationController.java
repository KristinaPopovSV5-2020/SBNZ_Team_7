package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.BudgetDTO;
import com.ftn.sbnz.dto.product.RecommendedDTO;
import com.ftn.sbnz.exception.NotFoundException;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }


    @PostMapping("/products")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecommendedDTO>> recommendProducts(@RequestBody BudgetDTO budgetDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }
        List<RecommendedDTO> recommendedProducts = recommendationService.recommendProductsForUser(user.getId(), budgetDTO);
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
