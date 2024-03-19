package com.magattech.certGen;

import com.magattech.certGen.model.Kompanija;
import com.magattech.certGen.model.Role;
import com.magattech.certGen.model.User;
import com.magattech.certGen.model.VrstaKontrolisanja;
import com.magattech.certGen.repository.KompanijaRepository;
import com.magattech.certGen.repository.UserRepository;
import com.magattech.certGen.repository.VrstaKontrolisanjaRepository;
import com.magattech.certGen.service.VrstaKontrolisanjaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataLoader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VrstaKontrolisanjaRepository vrstaKontrolisanjaRepository;
    private final KompanijaRepository kompanijaRepository;

    @Autowired
    private DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, VrstaKontrolisanjaRepository vrstaKontrolisanjaRepository, KompanijaRepository kompanijaRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vrstaKontrolisanjaRepository = vrstaKontrolisanjaRepository;
        this.kompanijaRepository = kompanijaRepository;
    }

    @PostConstruct
    public void addDummyData(){
        User adminUser = User.builder()
                .firstName("Admin")
                .lastName("Adminovic")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("123"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(adminUser);

        User regularUser = User.builder()
                .firstName("Regular")
                .lastName("Userovic")
                .email("user@gmail.com")
                .password(passwordEncoder.encode("123"))
                .role(Role.USER)
                .build();
        userRepository.save(regularUser);

        VrstaKontrolisanja vrstaKontrolisanja = VrstaKontrolisanja.builder().description("Redovno (periodicno)").build();
        vrstaKontrolisanjaRepository.save(vrstaKontrolisanja);

        VrstaKontrolisanja vrstaKontrolisanja1 = VrstaKontrolisanja.builder().description("Vanredno").build();
        vrstaKontrolisanjaRepository.save(vrstaKontrolisanja1);

        Kompanija kompanija = Kompanija.builder().name("Kompanija 1").build();
        kompanijaRepository.save(kompanija);
        Kompanija kompanija1 = Kompanija.builder().name("Kompanija 2").build();
        kompanijaRepository.save(kompanija1);

    }
}
