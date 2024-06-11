package com.ftn.sbnz.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import com.ftn.sbnz.model.models.Ingredient;
import org.bson.types.ObjectId;

public class IngredientDTO {

    private String name;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private boolean allergen;

    private boolean special;


    public IngredientDTO() {
    }

    public IngredientDTO(String name, boolean allergen, boolean special) {
        this.name = name;
        this.allergen = allergen;
        this.special = special;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.allergen = ingredient.isAllergen();
        this.id = ingredient.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllergen() {
        return allergen;
    }

    public void setAllergen(boolean allergen) {
        this.allergen = allergen;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
