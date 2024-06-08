package com.ftn.sbnz.dto.reports;

import com.ftn.sbnz.model.models.Feedback;
import org.bson.types.ObjectId;

public class FeedbackUserDTO {

    private ObjectId userId;

    private String email;

    private Integer amountOfFeedback;


    public FeedbackUserDTO(ObjectId userId, String email, Integer amountOfFeedback) {
        this.userId = userId;
        this.email = email;
        this.amountOfFeedback = amountOfFeedback;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAmountOfFeedback() {
        return amountOfFeedback;
    }

    public void setAmountOfFeedback(Integer amountOfFeedback) {
        this.amountOfFeedback = amountOfFeedback;
    }
}
