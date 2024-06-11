package com.ftn.sbnz.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.products.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Optional<Product> findById(ObjectId id);
    
}
