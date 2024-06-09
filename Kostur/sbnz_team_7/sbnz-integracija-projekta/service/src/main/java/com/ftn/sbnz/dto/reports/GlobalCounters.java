package com.ftn.sbnz.dto.reports;

public class GlobalCounters {
    private double totalShoppingValue = 0.0;
    private int totalShoppingCount = 0;

    public void addValue(double value) {
        this.totalShoppingValue += value;
    }

    public void incrementCount() {
        this.totalShoppingCount++;
    }

    public double getTotalShoppingValue() {
        return totalShoppingValue;
    }

    public int getTotalShoppingCount() {
        return totalShoppingCount;
    }
}
