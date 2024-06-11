package com.ftn.sbnz.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, ObjectId>{

    Ingredient findIngredientByName(String name);
    
}
