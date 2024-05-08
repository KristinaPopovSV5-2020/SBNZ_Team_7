package com.ftn.sbnz.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.Feedback;

public interface IFeedbackRepository  extends MongoRepository<Feedback, ObjectId>{
    
}
