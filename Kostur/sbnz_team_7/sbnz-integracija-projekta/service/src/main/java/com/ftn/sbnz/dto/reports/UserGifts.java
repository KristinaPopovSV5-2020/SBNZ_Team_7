package com.ftn.sbnz.dto.reports;

import com.ftn.sbnz.model.models.Gift;
import com.ftn.sbnz.model.models.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserGifts {
    private User user;
    private List<Gift> gifts;

    public UserGifts(User user) {
        this.user = user;
        this.gifts = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public void addGift(Gift gift) {
        this.gifts.add(gift);
    }


    public UserGifts(User user, List<Gift> gifts) {
        this.user = user;
        this.gifts = gifts;
    }
}