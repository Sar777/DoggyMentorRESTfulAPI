package io.github.doggymentor.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 91901774547107674L;

    private String email;

    private String username;

    private String password;

    private Integer gender;

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getGender() {
        return gender;
    }
}
