package com.ftn.sbnz.facts;

import com.ftn.sbnz.model.models.products.Shopping;

public class ShoppingStatus {
    private Shopping shopping;
    private boolean processed;

    public ShoppingStatus(Shopping shopping, boolean processed) {
        this.shopping = shopping;
        this.processed = processed;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}