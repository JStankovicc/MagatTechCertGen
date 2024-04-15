package com.magattech.certGen;

import com.magattech.certGen.model.*;
import com.magattech.certGen.model.enums.Role;
import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Oprema;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.merila.*;
import com.magattech.certGen.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Component
public class DataLoader {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VrstaKontrolisanjaRepository vrstaKontrolisanjaRepository;
    private final KompanijaRepository kompanijaRepository;
    private final ProizvodjacRepository proizvodjacRepository;
    private final OpremaRepository opremaRepository;

    private final JednodelnoMeriloRepository jednodelnoMeriloRepository;
    private final MasinaZaMerenjeRepository masinaZaMerenjeRepository;
    private final MernaLetvaRepository mernaLetvaRepository;

    private final MernaTrakaSaViskomRepository mernaTrakaSaViskomRepository;
    private final MetriZaTekstilRepository metriZaTekstilRepository;
    private final SlozivoMeriloRepository slozivoMeriloRepository;

    @Autowired
    private DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, VrstaKontrolisanjaRepository vrstaKontrolisanjaRepository, KompanijaRepository kompanijaRepository, ProizvodjacRepository proizvodjacRepository, OpremaRepository opremaRepository, JednodelnoMeriloRepository jednodelnoMeriloRepository, MasinaZaMerenjeRepository masinaZaMerenjeRepository, MernaLetvaRepository mernaLetvaRepository, MernaTrakaSaViskomRepository mernaTrakaSaViskomRepository, MetriZaTekstilRepository metriZaTekstilRepository, SlozivoMeriloRepository slozivoMeriloRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vrstaKontrolisanjaRepository = vrstaKontrolisanjaRepository;
        this.kompanijaRepository = kompanijaRepository;
        this.proizvodjacRepository = proizvodjacRepository;
        this.opremaRepository = opremaRepository;
        this.jednodelnoMeriloRepository = jednodelnoMeriloRepository;
        this.masinaZaMerenjeRepository = masinaZaMerenjeRepository;
        this.mernaLetvaRepository = mernaLetvaRepository;
        this.mernaTrakaSaViskomRepository = mernaTrakaSaViskomRepository;
        this.metriZaTekstilRepository = metriZaTekstilRepository;
        this.slozivoMeriloRepository = slozivoMeriloRepository;
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

        proizvodjacRepository.save(Proizvodjac.builder().name("Proizvodjac").build());
        proizvodjacRepository.save(Proizvodjac.builder().name("Proizvodjac 2").build());

        opremaRepository.save(Oprema.builder().name("Oprema").serBrEtalona("SerBr1").build());
        opremaRepository.save(Oprema.builder().name("Oprema 2").serBrEtalona("SerBr2").build());

        jednodelnoMeriloRepository.save(JednodelnoMerilo.builder().brojZapisnika("47/23-K1").datum(new Date()).build());

        masinaZaMerenjeRepository.save(MasinaZaMerenje.builder().brojZapisnika("39/23-K2").datum(new Date()).build());

        mernaLetvaRepository.save(MernaLetva.builder().brojZapisnika("45/23-K1").datum(new Date()).build());

        mernaTrakaSaViskomRepository.save(MernaTrakaSaViskom.builder().brojZapisnika("46/23-K1").datum(new Date()).build());

        metriZaTekstilRepository.save(MetriZaTekstil.builder().brojZapisnika("47/23-K1").datum(new Date()).build());

        slozivoMeriloRepository.save(SlozivoMerilo.builder().brojZapisnika("47/23-K1").datum(new Date()).build());

    }
}
