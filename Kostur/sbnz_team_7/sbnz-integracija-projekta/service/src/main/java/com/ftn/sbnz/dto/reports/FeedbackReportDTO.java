package com.ftn.sbnz.dto.reports;

import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.reports.FeedbackReport;

import java.util.ArrayList;
import java.util.List;

public class FeedbackReportDTO {

    private String productId;

    private List<FeedbackDTO> feedbacks;

    private double averageRating;

    public FeedbackReportDTO(String productId, List<FeedbackDTO> feedbacks, double averageRating) {
        this.productId = productId;
        this.feedbacks = feedbacks;
        this.averageRating = averageRating;
    }

    public FeedbackReportDTO(){}


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
