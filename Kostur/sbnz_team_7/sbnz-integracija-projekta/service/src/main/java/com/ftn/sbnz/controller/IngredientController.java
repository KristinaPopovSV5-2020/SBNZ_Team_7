package com.ftn.sbnz.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.service.IngredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api/ingredient",produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;


    @PostMapping
    public ResponseEntity<Boolean> addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        System.out.println("BLA");
      
        Ingredient savedIngredient = ingredientService.save(ingredientDTO);


        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
}
