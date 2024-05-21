package com.ftn.sbnz.facts;

import org.bson.types.ObjectId;

public class CategoryFact {

    private ObjectId objectId;

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public CategoryFact(ObjectId objectId) {
        this.objectId = objectId;
    }
}
