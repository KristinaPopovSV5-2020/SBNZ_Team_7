package com.ftn.sbnz.service.impl;

import com.ftn.sbnz.dto.product.ProductCategoryDTO;
import com.ftn.sbnz.model.models.products.ProductCategory;
import com.ftn.sbnz.repository.CategoryRepository;
import com.ftn.sbnz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ProductCategory save(ProductCategoryDTO dto) {
        Optional<ProductCategory> existingCategory = categoryRepository.findByParentIdAndName(dto.getParentId(), dto.getName());
        if (existingCategory.isEmpty()) {
            ProductCategory category = new ProductCategory();
            category.setName(dto.getName());
            category.setLevel(dto.getLevel());
            category.setParentId(dto.getParentId());
            return categoryRepository.save(category);
        }
        return existingCategory.get();
    }
}
