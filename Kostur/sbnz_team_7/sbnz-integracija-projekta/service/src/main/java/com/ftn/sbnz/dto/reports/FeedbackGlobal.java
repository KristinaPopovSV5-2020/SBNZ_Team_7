package com.ftn.sbnz.dto.reports;

public class FeedbackGlobal {

    private int feedbackCount = 0;

    public void incrementFeedbackCount() {
        feedbackCount++;
    }

    public int getFeedbackCount() {
        return feedbackCount;
    }
}
