package com.ftn.sbnz.service.impl;

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

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private KieSession cepKsession;

    @Override
    public List<Product> recommendProductsForUser(ObjectId userId) {
        User user = userRepository.findById(userId).orElseThrow();      // TODO: handle exception
        List<Product> allProducts = productService.findAll();

        List<Product> products = new ArrayList<>();
        cepKsession.setGlobal("recommendedProducts", products);
        cepKsession.insert(user);
        allProducts.forEach(cepKsession::insert);

        cepKsession.fireAllRules();

        List<Product> recommendedProducts = (List<Product>) cepKsession.getGlobal("recommendedProducts");

        cepKsession.dispose();

        return recommendedProducts;
    }
}
