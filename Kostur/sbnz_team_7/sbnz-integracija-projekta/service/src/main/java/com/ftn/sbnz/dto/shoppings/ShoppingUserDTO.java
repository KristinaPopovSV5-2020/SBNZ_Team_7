package com.ftn.sbnz.dto.shoppings;


import com.ftn.sbnz.model.models.enums.SkinBenefit;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.products.Shopping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingUserDTO {

    private String productId;

    private Date date;

    private String name;


    private List<String> benefits;

    private double price;

    public ShoppingUserDTO(){

    }

    public ShoppingUserDTO(String productId,Date date,String name, List<String> benefits, double price) {
        this.productId = productId;
        this.date = date;
        this.name = name;
        this.benefits = benefits;
        this.price = price;
    }

    public ShoppingUserDTO(Shopping shopping, Product product) {
        this.productId = product.getId().toString();
        this.date = shopping.getDateTime();
        this.name = product.getName();
        this.benefits =product.getBenefits().stream()
                .map(SkinBenefit::name)
                .collect(Collectors.toList());
        this.price = shopping.getValue();

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<String> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
