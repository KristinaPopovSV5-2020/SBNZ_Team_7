package com.ftn.sbnz.service.impl;


import com.ftn.sbnz.facts.RecommendedProduct;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.service.ProductService;
import com.ftn.sbnz.service.RecommendationService;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private KieSession cepKsession;

    @Autowired
    private KieContainer kieContainer;



    @Override
    public List<Product> recommendProductsForUser(ObjectId userId) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            List<Product> allProducts = productService.findAll();

            List<RecommendedProduct> recommendedProducts = new ArrayList<>();
            kieSession.setGlobal("recommendedProducts", recommendedProducts);
            kieSession.insert(user);
            allProducts.forEach(kieSession::insert);
            int fired = kieSession.fireAllRules();

            System.out.println("ispaljeno" + fired);

            return recommendedProducts.stream()
                    .map(RecommendedProduct::getProduct)
                    .collect(Collectors.toList());


        } finally {
            kieSession.dispose();
        }
    }
}
