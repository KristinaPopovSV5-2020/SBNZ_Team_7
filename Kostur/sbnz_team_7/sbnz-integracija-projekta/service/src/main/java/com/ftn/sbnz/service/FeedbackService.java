package com.ftn.sbnz.service;


import com.ftn.sbnz.dto.product.FeedbackDTO;
import com.ftn.sbnz.model.models.Feedback;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public interface FeedbackService {
    Feedback save(FeedbackDTO feedbackDTO, ObjectId userId);
}
