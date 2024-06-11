package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.Discount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends MongoRepository<Discount, ObjectId> {


    public List<Discount> findByUserId(ObjectId userId);


    List<Discount> findByUserIdAndUsedFalse(ObjectId userId);
}
