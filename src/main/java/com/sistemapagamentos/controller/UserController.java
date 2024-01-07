package com.sistemapagamentos.controller;

import com.sistemapagamentos.DTOs.UserRequest;
import com.sistemapagamentos.entity.User;
import com.sistemapagamentos.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequest request) {
        User user = request.toModel(request);
        return ResponseEntity.ok().body(service.registerUser(user));
    }



}
