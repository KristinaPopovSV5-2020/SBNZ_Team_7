package com.ftn.sbnz.model.models;

import java.time.LocalDateTime;

public class Gift {
    private String giftName;
    private LocalDateTime timeGiven;
    private String reason;

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

    public Gift(String giftName, LocalDateTime timeGiven, String reason) {
        this.giftName = giftName;
        this.timeGiven = timeGiven;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
