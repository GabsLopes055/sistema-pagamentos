package com.sistemapagamentos.services;

import com.sistemapagamentos.Utils.RandomString;
import com.sistemapagamentos.config.SecurityConfig;
import com.sistemapagamentos.entity.User;
import com.sistemapagamentos.repository.UserRepository;
import com.sistemapagamentos.services.Exceptions.EmailInUse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    private final SecurityConfig encoder;

    private UserService(UserRepository repository, SecurityConfig encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User registerUser(User user) {

        if(repository.findByEmail(user.getEmail()) != null)
            throw new EmailInUse("Email: " + user.getEmail() + " já esta em uso");
        else {

            user.setPassword(encoder.encoderPassword().encode(user.getPassword()));

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);

            user.setEnabled(false);

            User userSaved = repository.save(user);

            return userSaved;
        }

    }


}
