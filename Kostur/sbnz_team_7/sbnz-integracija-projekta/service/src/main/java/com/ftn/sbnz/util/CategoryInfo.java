package com.ftn.sbnz.util;

import org.bson.types.ObjectId;

public class CategoryInfo {
    private ObjectId lowestCategoryId;
    private String path;

    // Constructor, getters, and setters
    public CategoryInfo(ObjectId lowestCategoryId, String path) {
        this.lowestCategoryId = lowestCategoryId;
        this.path = path;
    }

    public ObjectId getLowestCategoryId() {
        return lowestCategoryId;
    }

    public void setLowestCategoryId(ObjectId lowestCategoryId) {
        this.lowestCategoryId = lowestCategoryId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}