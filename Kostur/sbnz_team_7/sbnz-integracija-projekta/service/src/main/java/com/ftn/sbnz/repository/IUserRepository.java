package com.ftn.sbnz.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ftn.sbnz.model.models.User;

public interface IUserRepository extends MongoRepository<User, ObjectId> {

    public User findByUsernameAndPassword(String username, String password);
    UserDetails findByUsername(String username);
    
}
