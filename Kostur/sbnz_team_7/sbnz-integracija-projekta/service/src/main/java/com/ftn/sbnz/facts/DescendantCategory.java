package com.ftn.sbnz.facts;

import org.bson.types.ObjectId;

public class DescendantCategory {
    private ObjectId id;

    public DescendantCategory(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }
}
