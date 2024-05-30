package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.service.implementation.IngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {


    private final IngredientServiceImpl ingredientService;

    @Autowired
    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addIngredient(@RequestBody IngredientDTO ingredientDTO) {

        Ingredient savedIngredient = ingredientService.save(ingredientDTO);


        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
