package com.ftn.sbnz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.Product;

public interface IProductRepository extends MongoRepository<Product, Long> {
    
}
