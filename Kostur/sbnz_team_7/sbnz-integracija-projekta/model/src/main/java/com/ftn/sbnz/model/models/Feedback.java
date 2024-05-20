package com.ftn.sbnz.model.models;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import com.ftn.sbnz.model.models.products.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "feedbacks")
public class Feedback {

    @MongoId
	ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId productId;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    private Integer rating;

    private LocalDateTime dateTime;

    public Feedback(){
        super();
    }
    

    public Feedback(ObjectId id, ObjectId product, ObjectId userId, Integer rating, LocalDateTime dateTime) {
        super();
        this.id = id;
        this.productId = product;
        this.userId = userId;
        this.rating = rating;
        this.dateTime = dateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProduct() {
        return productId;
    }


    public void setProduct(ObjectId product) {
        this.productId = product;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(productId, feedback.productId) && Objects.equals(userId, feedback.userId) && Objects.equals(rating, feedback.rating) && Objects.equals(dateTime, feedback.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, userId, rating, dateTime);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
