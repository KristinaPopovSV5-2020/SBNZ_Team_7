package com.ftn.sbnz.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IIngredientRepository extends MongoRepository<Ingredient, ObjectId>{
    
}
