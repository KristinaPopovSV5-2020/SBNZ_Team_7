package com.ftn.sbnz.dto.user;

import com.ftn.sbnz.model.models.user.User;

public class UserReportDTO {

    private String userId;

    private String username;

    public UserReportDTO(User user){
        this.userId = user.getId().toString();
        this.username = user.getUsername();
    }

    public UserReportDTO(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
