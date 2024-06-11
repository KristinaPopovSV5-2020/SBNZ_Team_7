package com.ftn.sbnz.dto.reports;

import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.reports.FeedbackReport;

import java.util.ArrayList;
import java.util.List;

public class FeedbackReportDTO {

    private String productName;

    private List<FeedbackDTO> feedbacks;

    private double averageRating;

    public FeedbackReportDTO(String productName, List<FeedbackDTO> feedbacks, double averageRating) {
        this.productName = productName;
        this.feedbacks = feedbacks;
        this.averageRating = averageRating;
    }

    public FeedbackReportDTO(){}


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<FeedbackDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
