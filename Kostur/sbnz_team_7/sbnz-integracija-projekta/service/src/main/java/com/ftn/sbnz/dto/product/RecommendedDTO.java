package com.ftn.sbnz.dto.product;

import com.ftn.sbnz.facts.RecommendedProduct;
import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.enums.SkinType;
import com.ftn.sbnz.model.models.products.Product;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendedDTO {
    private String id;
    private String path;
    private double price;
    private String instruction;
    private boolean vegan;
    private List<SkinType> skinTypes;
    private List<SkinBenefit> benefits;
    private String name;

    private double score;

    private String reason;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<String> ingredientIds;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public List<String> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<String> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public RecommendedDTO() {}

    public RecommendedDTO(Product product, RecommendedProduct recommendedProduct) {
        this.id = product.getId().toString();
        this.path = product.getPath();
        this.price = product.getPrice();
        this.instruction = product.getInstruction();
        this.vegan = product.isVegan();
        this.skinTypes = new ArrayList<>(product.getSkinTypes());
        this.benefits = new ArrayList<>(product.getBenefits());
        if (product.getIngredientIds() != null) {
            this.ingredientIds = product.getIngredientIds().stream()
                    .map(ObjectId::toString)
                    .collect(Collectors.toList());
        }
        this.name = product.getName();
        this.score = recommendedProduct.getScore();
        this.reason = recommendedProduct.getReason();
    }
}
