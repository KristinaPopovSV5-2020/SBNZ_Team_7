package com.ftn.sbnz.dto.product;


import com.ftn.sbnz.model.models.products.ProductCategory;
import org.bson.types.ObjectId;

public class ProductCategoryDTO {

    private String name;

    private int level;

    private ObjectId parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ObjectId getParentId() {
        return parentId;
    }

    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }

    public ProductCategoryDTO(String name, int level, ObjectId parentId) {
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }

    public ProductCategoryDTO(ProductCategory category) {
        this.name = category.getName();
        this.level = category.getLevel();
        this.parentId = category.getParentId();
    }
}