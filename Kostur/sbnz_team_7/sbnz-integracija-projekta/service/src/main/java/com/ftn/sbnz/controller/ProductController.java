package com.ftn.sbnz.controller;
import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.service.IngredientService;
import com.ftn.sbnz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/products",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {


    @Autowired
    private ProductService productService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addProduct(@RequestBody ProductDTO productDTO) {

        Product product = productService.save(productDTO);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
