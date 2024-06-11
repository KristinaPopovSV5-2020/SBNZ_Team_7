package com.ftn.sbnz.dto.product;

import java.util.Objects;

public class FeedbackDTO {

    private String productId;

    private Integer rating;


    public FeedbackDTO(){
        super();
    }


    public FeedbackDTO(String productId, Integer rating) {
        this.productId = productId;
        this.rating = rating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        FeedbackDTO that = (FeedbackDTO) o;
        return  Objects.equals(productId, that.productId) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, rating);
    }
}
