package com.ftn.sbnz.dto.reports;

public class DicountUsageReportDTO {

    private Integer totalNumber;
    private Integer totalUsed;
    private double percentageUsed;


    public DicountUsageReportDTO() {
    }

    public DicountUsageReportDTO(Integer totalNumber, Integer totalUsed, double percentageUsed) {
        this.totalNumber = totalNumber;
        this.totalUsed = totalUsed;
        this.percentageUsed = percentageUsed;

    }


    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTotalUsed() {
        return totalUsed;
    }

    public void setTotalUsed(Integer totalUsed) {
        this.totalUsed = totalUsed;
    }

    public double getPercentageUsed() {
        return percentageUsed;
    }

    public void setPercentageUsed(double percentageUsed) {
        this.percentageUsed = percentageUsed;
    }


    public void calculatePercentageUsed() {
        if (totalNumber > 0) {
            percentageUsed = ((double) totalUsed / totalNumber) * 100;
        } else {
            percentageUsed = 0.0;
        }
    }
}
