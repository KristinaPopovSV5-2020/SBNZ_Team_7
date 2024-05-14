package com.ftn.sbnz.repository;


import com.ftn.sbnz.model.models.user.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, ObjectId> {

    Role findRoleByName(String name);
}
