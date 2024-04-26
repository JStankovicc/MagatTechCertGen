package com.magattech.certGen.service;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<User> findAll();

    void addUser(UserRequest userRequest);

    void deleteUserByEmail(String email);
}