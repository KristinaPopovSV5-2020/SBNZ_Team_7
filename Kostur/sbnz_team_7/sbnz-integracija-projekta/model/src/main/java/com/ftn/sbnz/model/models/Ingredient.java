package com.ftn.sbnz.model.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

    private String name;

    private boolean allergen; 

    private boolean special;

    @ManyToMany(mappedBy = "ingredients")
    private List<Product> products;

    public Ingredient() {
        super();
    }

    

    public Ingredient(Long id, String name, boolean allergen, boolean special) {
        this.id = id;
        this.name = name;
        this.allergen = allergen;
        this.special = special;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (allergen != other.allergen)
            return false;
        if (special != other.special)
            return false;
        return true;
    }

    
}
