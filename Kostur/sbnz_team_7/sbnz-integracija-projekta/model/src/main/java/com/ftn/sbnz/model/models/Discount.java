package com.ftn.sbnz.model.models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "discounts")
public class Discount {
    @JsonSerialize(using = ObjectIdSerializer.class)
    @MongoId
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;
    private double value;
    private Date dateCreated;
    private boolean used;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Discount() {
    }

    public Discount(ObjectId userId, double value, Date dateCreated, boolean used) {
        this.userId = userId;
        this.value = value;
        this.dateCreated = dateCreated;
        this.used = used;
    }
}
