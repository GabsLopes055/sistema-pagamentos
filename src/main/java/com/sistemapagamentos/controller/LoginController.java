package com.sistemapagamentos.controller;

import com.sistemapagamentos.DTOs.request.AuthenticationRequest;
import com.sistemapagamentos.DTOs.response.AuthenticationResponse;
import com.sistemapagamentos.entity.User;
import com.sistemapagamentos.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {


    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    LoginController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        AuthenticationResponse response = new AuthenticationResponse();

        response.toModelToken(token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }



}
