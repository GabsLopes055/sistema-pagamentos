package com.sistemapagamentos.DTOs.response;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse() {
    }

    public String toModelToken(String token) {
        this.setToken(token);
        return getToken();
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
