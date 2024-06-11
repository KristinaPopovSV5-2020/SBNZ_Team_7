package com.ftn.sbnz.dto;

import com.ftn.sbnz.model.models.Gift;

import java.time.LocalDateTime;

public class GiftDTO {
    private String giftName;
    private LocalDateTime timeGiven;
    private String reason;

    public GiftDTO() {
    }

    public GiftDTO(String giftName, LocalDateTime timeGiven, String reason) {
        this.giftName = giftName;
        this.timeGiven = timeGiven;
        this.reason = reason;
    }

    public GiftDTO(Gift gift) {
        this.giftName = gift.getGiftName();
        this.timeGiven = gift.getTimeGiven();
        this.reason = gift.getReason();
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public LocalDateTime getTimeGiven() {
        return timeGiven;
    }

    public void setTimeGiven(LocalDateTime timeGiven) {
        this.timeGiven = timeGiven;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
