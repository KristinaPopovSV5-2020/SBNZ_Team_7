package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.model.models.Ingredient;

public interface IngredientService {
    Ingredient save(IngredientDTO ingredientDTO);
}
