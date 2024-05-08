package com.ftn.sbnz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ftn.sbnz.model.models.User;

public interface IUserRepository extends MongoRepository<User, Long> {
    
}
