package com.ftn.sbnz.model.models;

import java.util.ArrayList;

import javax.persistence.*;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.enums.SkinType;

@Entity
@Document(collection = "products")
public class Product {

    @MongoId
	private ObjectId id;

    private double price;

    private String instruction;

    private boolean vegan;

    //@ElementCollection(targetClass = SkinType.class)
    @Enumerated(EnumType.STRING)
    private List<SkinType> skinTypes;
 
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cream_id")
    private List<SkinBenefit> benefits;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Feedback> reviews;

    @ManyToMany
    @JoinTable(
        name = "product_ingredient",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Product() {
        this.skinTypes = new ArrayList<SkinType>();
        this.benefits = new ArrayList<SkinBenefit>();
        this.reviews = new ArrayList<Feedback>();
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Product(ObjectId id, double price, String instruction, boolean vegan, List<SkinType> skinTypes,
            List<SkinBenefit> benefits, List<Feedback> reviews, List<Ingredient> ingredients) {
        this.id = id;
        this.price = price;
        this.instruction = instruction;
        this.vegan = vegan;
        this.skinTypes = skinTypes;
        this.benefits = benefits;
        this.reviews = reviews;
        this.ingredients = ingredients;
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

    public List<Feedback> getReviews() {
        return reviews;
    }

    public void setReviews(List<Feedback> reviews) {
        this.reviews = reviews;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    
    
}
