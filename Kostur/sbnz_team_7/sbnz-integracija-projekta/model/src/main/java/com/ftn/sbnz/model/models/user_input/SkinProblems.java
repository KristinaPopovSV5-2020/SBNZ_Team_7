package com.ftn.sbnz.model.models.user_input;

import com.ftn.sbnz.model.models.enums.SkinIssue;

import java.util.ArrayList;
import java.util.List;

public class SkinProblems {

    private List<SkinIssue> problems;

    public SkinProblems(){
        this.problems = new ArrayList<>();
    }

    public List<SkinIssue> getProblems() {
        return problems;
    }

    public void setProblems(List<SkinIssue> problems) {
        this.problems = problems;
    }
}
