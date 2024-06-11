package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.dto.product.ProductFeedbackDTO;
import com.ftn.sbnz.model.models.products.Product;

import java.util.List;


public interface ProductService {

    Product save(ProductDTO productDTO);

    Product save(Product product);
    List<Product> findAll();

    List<ProductFeedbackDTO> getAllProducts();




}
