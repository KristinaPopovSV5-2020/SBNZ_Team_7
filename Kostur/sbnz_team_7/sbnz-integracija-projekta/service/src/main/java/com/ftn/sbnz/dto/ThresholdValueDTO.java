package com.ftn.sbnz.dto;

public class ThresholdValueDTO {

    private double value;
    private boolean checkDate;


    public ThresholdValueDTO(double value, boolean checkDate) {
        this.value = value;
        this.checkDate = checkDate;
    }

    public ThresholdValueDTO() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isCheckDate() {
        return checkDate;
    }

    public void setCheckDate(boolean checkDate) {
        this.checkDate = checkDate;
    }
}
