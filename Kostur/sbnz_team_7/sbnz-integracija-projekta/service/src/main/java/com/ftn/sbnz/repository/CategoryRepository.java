package com.ftn.sbnz.repository;


import com.ftn.sbnz.model.models.products.ProductCategory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<ProductCategory, ObjectId> {


    Optional<ProductCategory> findByName(String name);
    Optional<ProductCategory> findByParentIdAndName(ObjectId id, String name);

}

