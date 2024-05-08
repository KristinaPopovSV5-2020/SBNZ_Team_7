package com.ftn.sbnz.service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz.dto.UserDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.model.models.enums.SkinType;
import com.ftn.sbnz.repository.IIngredientRepository;
import com.ftn.sbnz.repository.IRoleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.model.models.Role;
import com.ftn.sbnz.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IIngredientRepository ingredientRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(6,new SecureRandom());

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

    public User saveUser(UserDTO userDTO) throws IOException {
        if (loadUserByUsername(userDTO.getUsername()) != null){
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setSkinType(SkinType.valueOf(userDTO.getSkinType()));
        List<Ingredient> allergens = new ArrayList<>();
        for (String allergen: userDTO.getAllergens() ){
            allergens.add(ingredientRepository.findIngredientByName(allergen));
        }
        user.setAllergens(allergens);
        Role role = roleRepository.findRoleByName("User");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
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