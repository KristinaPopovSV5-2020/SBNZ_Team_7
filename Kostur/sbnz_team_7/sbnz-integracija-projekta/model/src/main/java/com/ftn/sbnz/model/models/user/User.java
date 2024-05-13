package com.ftn.sbnz.model.models.user;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.model.models.user.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.sbnz.model.models.enums.SkinType;

@Document(collection = "users")
public class User implements UserDetails{

    @MongoId
	private ObjectId id;

    private String username;
    private String password;

    private SkinType skinType;

    private List<Ingredient> allergens;

    private Timestamp lastPasswordResetDate;

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    public User(){
        super();
    }
    

    public User(ObjectId id, String username, String password, SkinType skinType, List<Ingredient> allergens) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.skinType = skinType;
        this.allergens = allergens;
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
    public boolean isEnabled() { return true; }



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

    

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    



    

    
    
}
