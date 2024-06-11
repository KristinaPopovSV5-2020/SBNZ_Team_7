package com.ftn.sbnz.facts;

import com.ftn.sbnz.model.models.products.Product;

public class RecommendedProduct {

    private Product product;
    private String reason;
    private double score;

    public RecommendedProduct(Product product, String reason) {
        this.product = product;
        this.reason = reason;
    }


    public RecommendedProduct(Product product, String reason, double score) {
        this.product = product;
        this.reason = reason;
        this.score = score;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
