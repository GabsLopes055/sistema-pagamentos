package com.sistemapagamentos.controller;

import com.sistemapagamentos.DTOs.request.UserRequest;
import com.sistemapagamentos.DTOs.response.UserResponse;
import com.sistemapagamentos.entity.User;
import com.sistemapagamentos.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;



    UserController(UserService service){
        this.service = service;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest request) throws MessagingException, UnsupportedEncodingException {
        User user = request.toModel(request);
        return ResponseEntity.ok().body(service.registerUser(user));
    }

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("code") String verifyCode) {
        if(service.verifyAccount(verifyCode)) {
            return "Conta verificada com Sucesso !";
        } else {
            return "Falha ao verificar conta do usuário";
        }
    }

    @GetMapping(value = "/teste")
    public String teste() {
        return "Você esta logado";
    }







}
