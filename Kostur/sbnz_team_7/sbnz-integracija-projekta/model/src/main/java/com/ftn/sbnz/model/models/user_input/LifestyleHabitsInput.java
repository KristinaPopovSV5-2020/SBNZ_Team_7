package com.ftn.sbnz.model.models.user_input;

import com.ftn.sbnz.model.models.enums.LifestyleHabits;

import java.util.ArrayList;
import java.util.List;

public class LifestyleHabitsInput {

    private List<LifestyleHabits> habits;

    public LifestyleHabitsInput(){
        this.habits = new ArrayList<>();
    }

    public List<LifestyleHabits> getHabits() {
        return habits;
    }

    public void setHabits(List<LifestyleHabits> habits) {
        this.habits = habits;
    }
}
