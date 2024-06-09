package com.ftn.sbnz.facts;

import com.ftn.sbnz.model.models.Feedback;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class FeedbackStatus {

    private Feedback feedback;
    private boolean processed;

    public FeedbackStatus(Feedback feedback, boolean processed) {
        this.feedback = feedback;
        this.processed = processed;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
