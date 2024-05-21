package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.products.Shopping;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingRepository extends MongoRepository<Shopping, ObjectId> {
}
