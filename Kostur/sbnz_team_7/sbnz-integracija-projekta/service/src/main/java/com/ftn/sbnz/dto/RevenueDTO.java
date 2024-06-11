package com.ftn.sbnz.dto;

public class RevenueDTO {
    private String period;
    private double totalRevenue;

    public RevenueDTO(String period, double totalRevenue) {
        this.period = period;
        this.totalRevenue = totalRevenue;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "RevenueDTO{" +
                "period='" + period + '\'' +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
