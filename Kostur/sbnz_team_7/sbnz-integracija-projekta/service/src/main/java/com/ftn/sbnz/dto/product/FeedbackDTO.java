package com.ftn.sbnz.dto.product;

import java.util.Objects;

public class FeedbackDTO {

    private String productId;

    private Integer rating;

    private boolean rated;

    public FeedbackDTO(){
        super();
    }


    public FeedbackDTO(String productId, Integer rating, boolean rated) {
        this.productId = productId;
        this.rating = rating;
        this.rated = rated;
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

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        return rated == that.rated && Objects.equals(productId, that.productId) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, rating, rated);
    }
}
