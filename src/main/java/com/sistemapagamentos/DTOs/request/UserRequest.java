package com.sistemapagamentos.DTOs.request;

import com.sistemapagamentos.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull(message = "O campo nome esta ausente")
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String name;

    @NotNull(message = "O campo email esta ausente")
    @NotBlank(message = "O campo email não pode ser vazio")
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull(message = "O campo password esta ausente")
    @NotBlank(message = "O campo password não pode ser vazio")
    @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
    private String password;

    @NotNull(message = "O campo password esta ausente")
    @NotBlank(message = "O campo password não pode ser vazio")
    private String role;


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User toModel(UserRequest request) {
        return new User(request.getName(), request.getEmail(), request.getPassword(), request.role);
    }
}
