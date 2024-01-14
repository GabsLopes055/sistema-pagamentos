package com.sistemapagamentos.services;

import com.sistemapagamentos.DTOs.response.UserResponse;
import com.sistemapagamentos.Utils.RandomString;
import com.sistemapagamentos.config.security.SecurityConfig;
import com.sistemapagamentos.entity.User;
import com.sistemapagamentos.repository.UserRepository;
import com.sistemapagamentos.services.Exceptions.EmailInUse;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    private final UserRepository repository;

    private final SecurityConfig encoder;

    private final MailService mailService;

    private UserService(UserRepository repository, SecurityConfig encoder, MailService mailService) {
        this.repository = repository;
        this.encoder = encoder;
        this.mailService = mailService;
    }

    public UserResponse registerUser(User user) throws MessagingException, UnsupportedEncodingException {

        if(repository.findByEmail(user.getEmail()) != null)
            throw new EmailInUse("Email: " + user.getEmail() + " já esta em uso");
        else {

            user.setPassword(encoder.encoderPassword().encode(user.getPassword()));

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);

            user.setEnabled(false);

            mailService.verificationMail(user);

            User userSaved = repository.save(user);


            return UserResponse.toModel(userSaved);
        }

    }

    public boolean verifyAccount(String verifyCode) {

        User verifyUser = repository.findByVerificationCode(verifyCode);

        if(verifyUser == null || verifyUser.isEnabled()) {
            return false;
        } else {
            verifyUser.setVerificationCode(null);
            verifyUser.setEnabled(true);
            repository.save(verifyUser);
            return true;
        }


    }


}
