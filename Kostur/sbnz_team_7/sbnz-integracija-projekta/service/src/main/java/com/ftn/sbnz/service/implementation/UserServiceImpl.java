package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.user.UserDTO;
import com.ftn.sbnz.model.models.Gift;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.model.models.enums.SkinType;
import com.ftn.sbnz.model.models.user.Role;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.repository.RoleRepository;
import com.ftn.sbnz.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(6, new SecureRandom());

    public User findOne(ObjectId id) {
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
        if (loadUserByUsername(userDTO.getUsername()) != null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setSkinType(SkinType.valueOf(userDTO.getSkinType()));
        List<Ingredient> allergens = new ArrayList<>();
        for (String allergen : userDTO.getAllergens()) {
            allergens.add(ingredientRepository.findIngredientByName(allergen));
        }
        user.setAllergens(allergens);
        Role role = roleRepository.findRoleByName("User");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void rewardUser(User user, Gift gift) {
        user.getGifts().add(gift);
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}