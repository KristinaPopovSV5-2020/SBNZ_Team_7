package com.ftn.sbnz.model.models;

public class RatingHelper {
    private Boolean canRate;

    public RatingHelper() {
        this.canRate = false;
    }

    public Boolean getCanRate() {
        return canRate;
    }

    public void setCanRate(Boolean canRate) {
        this.canRate = canRate;
    }
}
