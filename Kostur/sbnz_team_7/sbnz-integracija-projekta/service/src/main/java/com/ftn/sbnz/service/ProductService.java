package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.model.models.products.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product save(ProductDTO productDTO);
}
