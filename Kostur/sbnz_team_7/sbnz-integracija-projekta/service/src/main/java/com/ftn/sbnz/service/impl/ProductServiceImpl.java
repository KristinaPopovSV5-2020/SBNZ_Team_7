package com.ftn.sbnz.service.impl;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.dto.product.ProductSearchDTO;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.repository.ProductRepository;
import com.ftn.sbnz.service.ProductService;
import com.ftn.sbnz.util.ObjectMapper;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private KieContainer kieContainer;


    @Override
    public Product save(ProductDTO productDTO){
        Product product = ObjectMapper.productToEntity(productDTO, ingredientRepository);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }




}
