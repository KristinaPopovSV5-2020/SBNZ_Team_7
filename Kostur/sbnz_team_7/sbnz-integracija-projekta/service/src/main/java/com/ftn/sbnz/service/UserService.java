package com.ftn.sbnz.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    public User findOne(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    

    public User save(User user) {
        return userRepository.save(user);
    }


    public User tryLogin(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (user == null) {
            return  null;
        } else {
            return user;
        }
    }
}