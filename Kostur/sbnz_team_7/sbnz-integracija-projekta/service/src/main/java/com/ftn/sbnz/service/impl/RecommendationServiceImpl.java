package com.ftn.sbnz.service.impl;


import com.ftn.sbnz.dto.BudgetDTO;
import com.ftn.sbnz.dto.product.RecommendedDTO;
import com.ftn.sbnz.dto.product.ScoreReasonDTO;
import com.ftn.sbnz.facts.RecommendedProduct;
import com.ftn.sbnz.model.models.enums.LifestyleHabits;
import com.ftn.sbnz.model.models.enums.SkinIssue;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.model.models.user_input.LifestyleHabitsInput;
import com.ftn.sbnz.model.models.user_input.SkinProblems;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.service.ProductService;
import com.ftn.sbnz.service.RecommendationService;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;


    @Autowired
    private KieContainer kieContainer;


    @Override
    public List<RecommendedDTO> recommendProductsForUser(ObjectId userId, BudgetDTO budgetDTO) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            List<Product> allProducts = productService.findAll();

            List<RecommendedProduct> recommendedProducts = new ArrayList<>();
            kieSession.setGlobal("recommendedProducts", recommendedProducts);
            kieSession.insert(user);

            kieSession.insert(budgetDTO);
            allProducts.forEach(kieSession::insert);
            int fired = kieSession.fireAllRules();
            System.out.println(fired);  //  debugging :)


            return recommendedProducts.stream()
                    .map(recommendedProduct -> new RecommendedDTO(recommendedProduct.getProduct(), recommendedProduct))
                    .collect(Collectors.toList());

        } finally {
            kieSession.dispose();
        }
    }

    @Override
    public List<RecommendedDTO> recommendProductsBasedOnProblemsAndHabitsForUser(ObjectId userId, List<String> skinProblems, List<String> lifestyleHabits) {
        KieSession kieSession = kieContainer.newKieSession("forwardKsession");
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            List<Product> allProducts = productService.findAll();

            List<RecommendedProduct> recommendedProducts = new ArrayList<>();
            kieSession.setGlobal("recommendedProducts", recommendedProducts);
            kieSession.insert(user);
            allProducts.forEach(kieSession::insert);

            SkinProblems skinProblemsInput = new SkinProblems();
            List<SkinIssue> issues = new ArrayList<>();
            for (String p : skinProblems){
                SkinIssue skinIssue = SkinIssue.valueOf(p.toUpperCase());
                issues.add(skinIssue);
            }
            skinProblemsInput.setProblems(issues);
            kieSession.insert(skinProblemsInput);

            LifestyleHabitsInput lifestyleHabitsInput = new LifestyleHabitsInput();
            List<LifestyleHabits> habits = new ArrayList<>();
            for (String h: lifestyleHabits){
                LifestyleHabits habits1 = LifestyleHabits.valueOf(h.toUpperCase(Locale.ROOT));
                habits.add(habits1);
            }
            lifestyleHabitsInput.setHabits(habits);
            kieSession.insert(lifestyleHabitsInput);

            int fired = kieSession.fireAllRules();

            System.out.println("ispaljeno" + fired);

            // Mapa za praćenje proizvoda i njihovih skorova
            Map<Product, ScoreReasonDTO> productScores = new HashMap<>();
            for (RecommendedProduct rp : recommendedProducts) {
                Product product = rp.getProduct();
                // Ako proizvod već postoji u mapi, ažuriraj skor
                if (productScores.containsKey(product)) {
                    ScoreReasonDTO scoreReasonDTO = productScores.get(product);
                    double currentScore = scoreReasonDTO.getScore();
                    String currentReason = scoreReasonDTO.getReason();
                    scoreReasonDTO.setScore(currentScore + rp.getScore());
                    scoreReasonDTO.setReason(currentReason + ", " + rp.getReason());
                    productScores.put(product, scoreReasonDTO);
                    System.out.println("VEC SADRZI");
                    System.out.println(currentScore + rp.getScore());
                } else {
                    // Inače dodaj proizvod u mapu sa trenutnim skorom
                    productScores.put(product, new ScoreReasonDTO(rp.getScore(), rp.getReason()));
                    System.out.println("NE SADRZI");
                    System.out.println(rp.getScore());
                }
            }

            List<Entry<Product, ScoreReasonDTO>> sortedEntries = productScores.entrySet().stream()
                    .sorted(Comparator.comparing(entry -> entry.getValue(), Comparator.comparingDouble(ScoreReasonDTO::getScore).reversed()))
                    .collect(Collectors.toList());

            // Kreiranje liste RecommendedDTO iz sortiranih unosa mape
            List<RecommendedDTO> sortedProducts = sortedEntries.stream()
                    .map(entry -> new RecommendedDTO(entry.getKey(), entry.getValue().getScore(), entry.getValue().getReason()))
                    .collect(Collectors.toList());

            return sortedProducts;


        } finally {
            kieSession.dispose();
        }
    }

}
