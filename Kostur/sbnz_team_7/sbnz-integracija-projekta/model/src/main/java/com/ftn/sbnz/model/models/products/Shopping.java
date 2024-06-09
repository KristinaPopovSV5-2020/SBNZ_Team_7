package com.ftn.sbnz.model.models.products;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ftn.sbnz.model.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Role(Role.Type.EVENT)
@Timestamp("dateTime")
@Expires("30d")
@Document(collection = "shopping")
public class Shopping implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ObjectIdSerializer.class)
    @MongoId
    private ObjectId id;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId productId;
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId userId;

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    private Date dateTime;

    private boolean isNew;

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean aNew) {
        isNew = aNew;
    }


    public Shopping() {
        super();
    }


    public Shopping(ObjectId productId, ObjectId userId, Date date) {
        super();
        this.productId = productId;
        this.userId = userId;
        this.dateTime = date;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopping shopping = (Shopping) o;
        return Double.compare(value, shopping.value) == 0 && isNew == shopping.isNew && Objects.equals(id, shopping.id) && Objects.equals(productId, shopping.productId) && Objects.equals(userId, shopping.userId) && Objects.equals(dateTime, shopping.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, userId, dateTime);
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", value=" + value +
                ", dateTime=" + dateTime +
                ", isNew=" + isNew +
                '}';
    }
}
