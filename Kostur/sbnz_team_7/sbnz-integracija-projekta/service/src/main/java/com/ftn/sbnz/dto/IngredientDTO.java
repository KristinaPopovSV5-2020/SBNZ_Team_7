package com.ftn.sbnz.dto;

public class IngredientDTO {

    private String name;

    private boolean allergen; 

    private boolean special;

    

    public IngredientDTO() {
    }

    public IngredientDTO(String name, boolean allergen, boolean special) {
        this.name = name;
        this.allergen = allergen;
        this.special = special;
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

    
    
}
