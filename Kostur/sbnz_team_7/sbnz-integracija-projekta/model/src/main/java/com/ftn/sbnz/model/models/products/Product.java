package com.ftn.sbnz.model.models.products;

import java.util.ArrayList;

import javax.persistence.*;

import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.enums.SkinType;

@Document(collection = "products")
public class Product {

    @MongoId
    private ObjectId id;

    private String path;
    private double price;
    private String instruction;
    private boolean vegan;
    private List<SkinType> skinTypes;
    private List<SkinBenefit> benefits;

    private List<Ingredient> ingredients;


    public Product() {
        this.skinTypes = new ArrayList<SkinType>();
        this.benefits = new ArrayList<SkinBenefit>();
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Product(ObjectId id, double price, String instruction, boolean vegan, List<SkinType> skinTypes,
                   List<SkinBenefit> benefits, List<Ingredient> ingredients) {
        this.id = id;
        this.price = price;
        this.instruction = instruction;
        this.vegan = vegan;
        this.skinTypes = skinTypes;
        this.benefits = benefits;
        this.ingredients = ingredients;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public List<SkinType> getSkinTypes() {
        return skinTypes;
    }

    public void setSkinTypes(List<SkinType> skinTypes) {
        this.skinTypes = skinTypes;
    }

    public List<SkinBenefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<SkinBenefit> benefits) {
        this.benefits = benefits;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }





}
