package com.ftn.sbnz.model.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.sbnz.model.models.Gift;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.model.models.enums.SkinType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "users")
public class User extends BaseUser {

    @MongoId
    private ObjectId id;

    private String username;
    private String password;

    private SkinType skinType;

    private List<Ingredient> allergens;

    private List<Gift> gifts;

    public List<Gift> getGifts() {
        if (gifts == null) {
            gifts = new ArrayList<>();
        }
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public List<ObjectId> getAllergenIds() {
        return allergens.stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());
    }

    private Timestamp lastPasswordResetDate;

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    public User() {
        super();
    }


    public User(ObjectId id, String username, String password, SkinType skinType, List<Ingredient> allergens) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.skinType = skinType;
        this.allergens = allergens;
        this.gifts = new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.password = password;
    }


    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    public List<Ingredient> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Ingredient> allergens) {
        this.allergens = allergens;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }


    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}
