package com.magattech.certGen;

import com.magattech.certGen.model.Role;
import com.magattech.certGen.model.User;
import com.magattech.certGen.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataLoader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void addDummyData(){
        User adminUser = User.builder()
                .firstName("Admin")
                .lastName("Adminovic")
                .email("admin@google.com")
                .password(passwordEncoder.encode("123"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(adminUser);

        User regularUser = User.builder()
                .firstName("Regular")
                .lastName("Userovic")
                .email("user@google.com")
                .password(passwordEncoder.encode("123"))
                .role(Role.USER)
                .build();
        userRepository.save(regularUser);
    }
}
