package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.IngredientDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Ingredient> getAllergens() {
        List<Ingredient> allergens = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            if (ingredient.isAllergen()) {
                allergens.add(ingredient);
            }
        }
        return allergens;
    }

    @Override
    public List<IngredientDTO> getAll() {
        return ingredientRepository.findAll().stream()
                .map(IngredientDTO::new)
                .collect(Collectors.toList());
    }

}
