package com.ftn.sbnz.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedbackRepository  extends MongoRepository<Feedback, ObjectId>{
    
}
