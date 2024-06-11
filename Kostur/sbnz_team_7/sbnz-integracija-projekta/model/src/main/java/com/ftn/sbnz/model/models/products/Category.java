package com.ftn.sbnz.model.models.products;

import org.bson.types.ObjectId;
import org.kie.api.definition.type.Position;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "categories2")
public class Category {
    @MongoId
    @Position(0)
    private ObjectId id;
    @Position(1)
    private ObjectId parent;

    private String name;

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setChild(ObjectId child) {
        this.id = child;
    }

    public ObjectId getParent() {
        return parent;
    }

    public void setParent(ObjectId parent) {
        this.parent = parent;
    }

    public Category(ObjectId id, ObjectId parent) {
        this.id = id;
        this.parent = parent;
    }

    public Category(ObjectId id,String name, ObjectId parent) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    public Category() {

    }
}
