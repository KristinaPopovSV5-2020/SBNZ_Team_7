package com.ftn.sbnz.dto.reports;

public class FeedbackProductGlobal {

    private int feedbackCount = 0;

    public void incrementFeedbackCount() {
        feedbackCount++;
    }

    public int getFeedbackCount() {
        return feedbackCount;
    }
}
