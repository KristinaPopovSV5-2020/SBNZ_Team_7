package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Feedback;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, ObjectId> {


    public List<Feedback> findByUserId(ObjectId userId);

}

