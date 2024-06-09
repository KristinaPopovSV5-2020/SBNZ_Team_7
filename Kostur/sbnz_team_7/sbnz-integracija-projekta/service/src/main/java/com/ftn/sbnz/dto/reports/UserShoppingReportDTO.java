package com.ftn.sbnz.dto.reports;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class UserShoppingReportDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ObjectId userId;
    private String userEmail;
    private double totalValue;
    private Integer totalCount;

    private double average;

    public UserShoppingReportDTO() {
    }

    public UserShoppingReportDTO(ObjectId userId, double totalValue) {
        this.userId = userId;
        this.totalValue = totalValue;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public void calculateAverage() {
        this.average = totalValue / totalCount;
    }

    public double getAverage() {
        return average;
    }
}