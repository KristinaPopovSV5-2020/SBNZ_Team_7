package com.ftn.sbnz.model.models.reports;

import com.ftn.sbnz.model.models.Feedback;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public class FeedbackReport {
    private ObjectId productId;
    private List<Feedback> feedbacks;
    private double averageRating;

    public FeedbackReport(ObjectId productId, List<Feedback> feedbacks, double averageRating) {
        this.productId = productId;
        this.feedbacks = feedbacks;
        this.averageRating = averageRating;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackReport that = (FeedbackReport) o;
        return Double.compare(averageRating, that.averageRating) == 0 && Objects.equals(productId, that.productId) && Objects.equals(feedbacks, that.feedbacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, feedbacks, averageRating);
    }
}
