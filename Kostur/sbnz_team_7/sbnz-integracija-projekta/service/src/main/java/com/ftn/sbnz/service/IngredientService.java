package com.ftn.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.repository.IIngredientRepository;

@Service
public class IngredientService {

    @Autowired
    private IIngredientRepository ingredientRepository;


    public Ingredient save(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setAllergen(ingredientDTO.isAllergen());
        ingredient.setSpecial(ingredientDTO.isSpecial());

        return ingredientRepository.save(ingredient);
    }
    
}
