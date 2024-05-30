package com.ftn.sbnz.model.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.Objects;

@Role(Role.Type.EVENT)
@Timestamp("dateTime")
@Document(collection = "feedbacks")
public class Feedback {

    @MongoId
    ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId productId;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    private Integer rating;

    private Date dateTime;

    public Feedback() {
        super();
    }


    public Feedback(ObjectId id, ObjectId productId, ObjectId userId, Integer rating, Date dateTime) {
        super();
        this.id = id;
        this.productId = productId;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


}
