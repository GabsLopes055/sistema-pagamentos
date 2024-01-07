package com.sistemapagamentos.DTOs;

import com.sistemapagamentos.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String name;

    private String email;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toModel(UserRequest request) {
        return new User(request.getName(), request.getEmail(), request.getPassword());
    }
}
