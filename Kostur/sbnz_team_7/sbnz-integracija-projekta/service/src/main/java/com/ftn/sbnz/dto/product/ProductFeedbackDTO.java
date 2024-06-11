package com.ftn.sbnz.dto.product;

import com.ftn.sbnz.model.models.products.Product;

public class ProductFeedbackDTO {

    private String productId;

    private String name;

    public ProductFeedbackDTO(Product product){
        this.productId = product.getId().toString();
        this.name = product.getName();
    }

    public ProductFeedbackDTO(String productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
