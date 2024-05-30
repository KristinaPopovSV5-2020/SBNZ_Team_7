package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.model.models.products.Product;

import java.util.List;


public interface ProductService {

    Product save(ProductDTO productDTO);

    List<Product> findAll();


}
