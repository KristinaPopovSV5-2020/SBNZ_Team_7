package com.ftn.sbnz.dto.reports;

import java.util.ArrayList;
import java.util.List;

public class UserGiftReportDTO {
    private String username;
    private int giftCount;

    private List<String> giftNames;

    public List<String> getGiftNames() {
        return giftNames;
    }

    public void setGiftNames(List<String> giftNames) {
        this.giftNames = giftNames;
    }

    public UserGiftReportDTO(String username, List<String> giftNames) {
        this.username = username;
        if (giftNames.size() > 0) {
            this.giftNames = giftNames;
        } else {
            this.giftNames = new ArrayList<>();

        }
    }

    public UserGiftReportDTO(String username, int giftCount) {
        this.username = username;
        this.giftCount = giftCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }

    public void incrementGiftCount() {
        this.giftCount++;
    }

    public UserGiftReportDTO() {
    }

    @Override
    public String toString() {
        return "UserGiftReportDTO{" +
                "username='" + username + '\'' +
                ", giftCount=" + giftCount +
                '}';
    }
}