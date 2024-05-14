package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.product.ProductCategoryDTO;
import com.ftn.sbnz.model.models.products.ProductCategory;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    ProductCategory save(ProductCategoryDTO dto);
}
