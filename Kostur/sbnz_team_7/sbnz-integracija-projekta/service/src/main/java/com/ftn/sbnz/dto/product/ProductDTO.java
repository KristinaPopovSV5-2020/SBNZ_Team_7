package com.ftn.sbnz.dto.product;

import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.enums.SkinType;
import com.ftn.sbnz.model.models.products.Product;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {
    private String id;
    private String path;
    private double price;
    private String instruction;
    private boolean vegan;
    private List<SkinType> skinTypes;
    private List<SkinBenefit> benefits;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<String> ingredientIds;
    private List<String> ingredientNames;

    private ProductSearchDTO productSearchDTO;

    public ProductSearchDTO getProductSearchDTO() {
        return productSearchDTO;
    }

    public void setProductSearchDTO(ProductSearchDTO productSearchDTO) {
        this.productSearchDTO = productSearchDTO;
    }

    public String getId() {
        return id;
    }

    public List<String> getIngredientNames() {
        return ingredientNames;
    }

    public void setIngredientNames(List<String> ingredientNames) {
        this.ingredientNames = ingredientNames;
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

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
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
    }

    public ProductDTO(Product product, List<String> ingredientNames) {
        this.id = product.getId().toString();
        this.path = product.getPath();
        this.price = product.getPrice();
        this.instruction = product.getInstruction();
        this.vegan = product.isVegan();
        this.skinTypes = product.getSkinTypes();
        this.benefits = product.getBenefits();
        this.name = product.getName();
        this.ingredientNames = ingredientNames;
    }

}