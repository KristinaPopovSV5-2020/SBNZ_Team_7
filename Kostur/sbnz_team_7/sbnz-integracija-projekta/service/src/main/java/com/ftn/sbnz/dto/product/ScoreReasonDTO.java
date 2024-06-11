package com.ftn.sbnz.dto.product;

import java.util.List;

public class ScoreReasonDTO {

    private double score;
    private String reason;

    public ScoreReasonDTO(){

    }

    public ScoreReasonDTO(double score,String reason ){
        this.score = score;
        this.reason = reason;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
