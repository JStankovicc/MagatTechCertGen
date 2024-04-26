package com.magattech.certGen.service.implementation;

import com.magattech.certGen.configuration.SecurityConfiguration;
import com.magattech.certGen.model.User;
import com.magattech.certGen.model.enums.Role;
import com.magattech.certGen.model.request.UserRequest;
import com.magattech.certGen.repository.UserRepository;
import com.magattech.certGen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserRequest userRequest) {

        String password = userRequest.getPassword();

        User user = User.builder().firstName(userRequest.getFirstName()).lastName(userRequest.getLastName()).email(userRequest.getEmail()).password(new BCryptPasswordEncoder().encode(password)).build();
        if(userRequest.getRole().equals("admin")) {
            user.setRole(Role.ADMIN);
        }else user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        userRepository.delete(user);
    }
}