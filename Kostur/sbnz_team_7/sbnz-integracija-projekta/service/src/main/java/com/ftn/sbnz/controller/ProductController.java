package com.ftn.sbnz.controller;
import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.dto.product.ProductSearchDTO;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.service.CategoryService;
import com.ftn.sbnz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/products",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @PostMapping(value = "")
    public ResponseEntity<Boolean> addProduct(@RequestBody ProductDTO productDTO) {

        Product product = productService.save(productDTO);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/backward")
    public ResponseEntity<?> searchProducts(@RequestBody ProductSearchDTO searchDTO) {
        List<ProductDTO> productDTOS = categoryService.searchBackward(searchDTO);
        return ResponseEntity.ok(productDTOS);
    }



}
