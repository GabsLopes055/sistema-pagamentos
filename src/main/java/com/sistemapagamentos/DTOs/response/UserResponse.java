package com.sistemapagamentos.DTOs.response;

import com.sistemapagamentos.entity.User;

import java.util.UUID;

public class UserResponse {

    private UUID id;

    private String name;

    private String email;

    private String password;

    public UserResponse(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserResponse toModel(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public UserResponse() {
    }

    public UUID getId() {
        return id;
    }

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
}
