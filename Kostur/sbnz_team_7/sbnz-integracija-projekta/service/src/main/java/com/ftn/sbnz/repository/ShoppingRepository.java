package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.products.Shopping;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShoppingRepository extends MongoRepository<Shopping, ObjectId> {

    List<Shopping> findByUserId(ObjectId userId);
}
