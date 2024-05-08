package com.ftn.sbnz.model.models;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ftn.sbnz.model.models.enums.SkinType;

@Entity
@Document(collection = "users")
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private SkinType skinType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Ingredient> allergens;

    public User(){
        super();
    }
    

    public User(Long id, String username, String password, SkinType skinType, List<Ingredient> allergens) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.skinType = skinType;
        this.allergens = allergens;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    



    

    
    
}
