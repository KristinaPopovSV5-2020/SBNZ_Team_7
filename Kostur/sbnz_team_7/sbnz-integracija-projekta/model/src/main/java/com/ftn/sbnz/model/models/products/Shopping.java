package com.ftn.sbnz.model.models.products;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "shopping")
public class Shopping {

    @JsonSerialize(using = ObjectIdSerializer.class)
    @MongoId
    private ObjectId id;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId productId;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    private LocalDateTime dateTime;

    public Shopping(){
        super();
    }


    public Shopping(ObjectId productId, ObjectId userId, LocalDateTime dateTime) {
        super();
        this.productId = productId;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopping shopping = (Shopping) o;
        return Objects.equals(id, shopping.id) && Objects.equals(productId, shopping.productId) && Objects.equals(userId, shopping.userId) && Objects.equals(dateTime, shopping.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, userId, dateTime);
    }
}
