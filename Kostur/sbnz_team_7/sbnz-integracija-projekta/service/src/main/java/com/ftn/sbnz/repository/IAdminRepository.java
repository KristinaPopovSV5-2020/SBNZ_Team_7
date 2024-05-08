package com.ftn.sbnz.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ftn.sbnz.model.models.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends MongoRepository<Admin, ObjectId>{
    UserDetails findByUsername(String username);

    public Admin findByUsernameAndPassword(String username, String password);

    
} 
