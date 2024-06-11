package com.ftn.sbnz.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import com.ftn.sbnz.model.models.Discount;
import org.bson.types.ObjectId;

import java.util.Date;

public class DiscountDTO {
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private double value;
    private Date dateCreated;
    private boolean used;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public DiscountDTO() {
    }

    public DiscountDTO(Discount discount) {
        this.id = discount.getId();
        this.used = discount.isUsed();
        this.value = discount.getValue();
        this.dateCreated = discount.getDateCreated();
    }


    public DiscountDTO(ObjectId id, double value, Date dateCreated, boolean used) {
        this.id = id;
        this.value = value;
        this.dateCreated = dateCreated;
        this.used = used;
    }
}
