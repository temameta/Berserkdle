package org.example.berserkdle.services;


import org.example.berserkdle.dtos.UserRegistrationDto;
import org.example.berserkdle.entities.User;

public interface AuthService {
    void register(UserRegistrationDto registrationDTO);

    User getUser(String username);
}
