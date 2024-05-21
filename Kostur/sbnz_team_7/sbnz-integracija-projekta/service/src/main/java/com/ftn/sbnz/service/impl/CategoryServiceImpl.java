package com.ftn.sbnz.service.impl;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.dto.product.ProductSearchDTO;
import com.ftn.sbnz.dto.product.RecommendedDTO;
import com.ftn.sbnz.facts.CategoryFact;
import com.ftn.sbnz.model.models.products.Category;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.repository.CategoryRepository;
import com.ftn.sbnz.service.CategoryService;
import com.ftn.sbnz.service.ProductService;
import org.apache.tomcat.jni.Proc;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private ProductService productService;



    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(ObjectId id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteById(ObjectId id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll(){
        categoryRepository.deleteAll();
    }
    @Override
    public List<ProductDTO> searchBackward(ProductSearchDTO productSearchDTO){
        KieSession kieSession = kieContainer.newKieSession("bwKsession");

        List<Product> foundProducts = new ArrayList<>();
        kieSession.setGlobal("foundProducts", foundProducts);

        List<Category> categories = categoryRepository.findAll();
        List<Product> allProducts = productService.findAll();
        // inserting into session
        for (Category category : categories) {
            kieSession.insert(category);
        }
        allProducts.forEach(kieSession::insert);


        ObjectId objectId = determineLowestCategoryId(productSearchDTO);
        CategoryFact categoryFact = new CategoryFact(objectId);
        kieSession.insert(categoryFact);

        int numOfFired = kieSession.fireAllRules();
        System.out.println(numOfFired);

        kieSession.dispose();


        return foundProducts.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());


    }

    private ObjectId determineLowestCategoryId(ProductSearchDTO productSearchDTO) {
        String typeCategory = productSearchDTO.getTypeCategory();
        String ageCategory = productSearchDTO.getAgeCategory();
        String usageCategory = productSearchDTO.getUsageCategory();

        ObjectId typeCategoryId = null;
        ObjectId ageCategoryId = null;
        ObjectId usageCategoryId = null;

        // Find the type category ID
        if (typeCategory != null) {
            Category typeCategoryObj = categoryRepository.findByNameAndParentIsNull(typeCategory);
            typeCategoryId = typeCategoryObj != null ? typeCategoryObj.getId() : null;
        }

        // Find the age category ID
        if (typeCategoryId != null && ageCategory != null) {
            Category ageCategoryObj = categoryRepository.findByNameAndParent(ageCategory, typeCategoryId);
            ageCategoryId = ageCategoryObj != null ? ageCategoryObj.getId() : null;
        }

        // Find the usage category ID
        if (ageCategoryId != null && usageCategory != null) {
            Category usageCategoryObj = categoryRepository.findByNameAndParent(usageCategory, ageCategoryId);
            usageCategoryId = usageCategoryObj != null ? usageCategoryObj.getId() : null;
        }

        // Return the lowest category ID
        if (usageCategoryId != null) {
            return usageCategoryId;
        } else if (ageCategoryId != null) {
            return ageCategoryId;
        } else if (typeCategoryId != null) {
            return typeCategoryId;
        } else {
            throw new IllegalArgumentException("Invalid category information provided.");
        }
    }

}
