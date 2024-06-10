package com.ftn.sbnz.dto.reports;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.util.ObjectIdSerializer;
import org.bson.types.ObjectId;

import java.util.List;

public class FeedbackNADto {
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId productId;

    private String name;


    private Integer numberOfProducts;

    private double average;

    public FeedbackNADto(){

    }

    public FeedbackNADto(ObjectId productId, String name, double average) {
        this.productId = productId;
        this.name = name;
        this.average = average;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

}
