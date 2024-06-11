package com.ftn.sbnz.dto.reports;

import com.ftn.sbnz.model.models.Feedback;

import java.util.Date;

public class FeedbackDTO {

    private String username;

    private Date date;

    private Integer rating;

    public FeedbackDTO(){}

    public FeedbackDTO(Feedback feedback, String username){
        this.username = username;
        this.date = feedback.getDateTime();
        this.rating = feedback.getRating();
    }

    public FeedbackDTO(String username, Date date, Integer rating) {
        this.username = username;
        this.date = date;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
