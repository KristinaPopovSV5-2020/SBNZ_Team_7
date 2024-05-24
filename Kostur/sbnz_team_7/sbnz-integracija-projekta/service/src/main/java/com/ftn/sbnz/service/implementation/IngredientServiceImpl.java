package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {


    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient save(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setAllergen(ingredientDTO.isAllergen());
        ingredient.setSpecial(ingredientDTO.isSpecial());

        return ingredientRepository.save(ingredient);
    }

}
