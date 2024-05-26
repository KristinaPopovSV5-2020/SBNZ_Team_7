package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import com.ftn.sbnz.model.models.products.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;


@Document(collection = "ingredients")
public class Ingredient {


    @JsonSerialize(using = ObjectIdSerializer.class)
    @MongoId
    private ObjectId id;

    private String name;

    private boolean allergen;

    private boolean special;

    private List<Product> products;

    public Ingredient() {
        super();
    }


    public Ingredient(ObjectId id, String name, boolean allergen, boolean special) {
        this.id = id;
        this.name = name;
        this.allergen = allergen;
        this.special = special;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
