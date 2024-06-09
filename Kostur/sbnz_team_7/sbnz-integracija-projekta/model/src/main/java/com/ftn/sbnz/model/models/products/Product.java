package com.ftn.sbnz.model.models.products;

import java.util.ArrayList;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdListSerializer;
import com.ftn.sbnz.model.ObjectIdSerializer;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.Ingredient;
import org.bson.types.ObjectId;
import org.kie.api.definition.type.Position;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.enums.SkinType;

@Document(collection = "products")
public class Product {

    @JsonSerialize(using = ObjectIdSerializer.class)
    @MongoId
    private ObjectId id;

    private String name;
    private String path;
    private double price;
    private String instruction;
    private boolean vegan;
    private List<SkinType> skinTypes;
    private List<SkinBenefit> benefits;

    private boolean popular;
    @Position(1)
    private ObjectId categoryId;

    public ObjectId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ObjectId categoryId) {
        this.categoryId = categoryId;
    }

    @JsonSerialize(using = ObjectIdListSerializer.class)
    private List<ObjectId> ingredientIds;

    public List<ObjectId> getIngredientIds() {
        return ingredientIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientIds(List<ObjectId> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public Product() {
        this.skinTypes = new ArrayList<SkinType>();
        this.benefits = new ArrayList<SkinBenefit>();
        this.popular = false;
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

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && vegan == product.vegan && popular == product.popular && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(path, product.path) && Objects.equals(instruction, product.instruction) && Objects.equals(skinTypes, product.skinTypes) && Objects.equals(benefits, product.benefits) && Objects.equals(categoryId, product.categoryId) && Objects.equals(ingredientIds, product.ingredientIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, price, instruction, vegan, skinTypes, benefits, popular, categoryId, ingredientIds);
    }
}
