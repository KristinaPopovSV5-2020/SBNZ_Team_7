package com.ftn.sbnz.model.models;

import org.bson.types.ObjectId;

public class RatingHelper {
    private Boolean canRate;

    private ObjectId productId;

    private Boolean canGenerateReport;

    public RatingHelper(ObjectId productId) {
        this.canRate = false;
        this.productId = productId;
        this.canGenerateReport = false;
    }

    public Boolean getCanRate() {
        return canRate;
    }

    public void setCanRate(Boolean canRate) {
        this.canRate = canRate;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public Boolean getCanGenerateReport() {
        return canGenerateReport;
    }

    public void setCanGenerateReport(Boolean canGenerateReport) {
        this.canGenerateReport = canGenerateReport;
    }
}
