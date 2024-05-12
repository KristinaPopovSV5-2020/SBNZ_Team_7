package com.ftn.sbnz.dto.user;

import java.util.List;

public class UserDTO {

    private String username;

    private String password;

    private String skinType;

    private List<String> allergens;

    public UserDTO(){
        super();
    }
    public UserDTO(String username, String password, String skinType, List<String> allergens) {
        this.username = username;
        this.password = password;
        this.skinType = skinType;
        this.allergens = allergens;
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

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }
}
