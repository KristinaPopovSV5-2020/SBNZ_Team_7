package com.ftn.sbnz.repository;

import com.ftn.sbnz.model.models.products.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Category findByNameAndParentIsNull(String category);

    Category findByNameAndParent(String category, ObjectId parentId);

}
