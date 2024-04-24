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

        jednodelnoMeriloRepository.save(JednodelnoMerilo.builder().brojZapisnika("47/23-K1").datum(new Date()).vrstaKontrolisanja("Periodicno (redovno)").podnosilacZahteva("Kompanija koja je podnela zahtev").korisnik("Kompanija koja je korisnik")
                .serijskiBroj("ser000").identifikacioniBroj("id000").proizvodjac("Proizvodjac").oznakaTipa("TIP1").sluzbenaOznakaTipa("TIP S1").meriloJeIspravno(true)
                .merniOpseg("0 mm do 1 mm").najmanjiPodeljak("1 cm").klasaTacnosti("III").temperatura("19.1").vlaznostVazduha("43,9")
                .napomena("Napomena").odstupanje1("-0,80").odstupanje2("0").odstupanje3("-0,90").odstupanje4("-0,90").odstupanje5("-1").ndg1("±2,6")
                .greska1("22-23").greska2("23-24").greska3("45-46").greska4("46-47").greska5("62-63").greska6("63-64").greska7("78-79").greska8("79-80")
                .greskaPodeljka1("+0.05").greskaPodeljka2("0").greskaPodeljka3("-0.10").greskaPodeljka4("0").greskaPodeljka5("-0.10").greskaPodeljka5("0")
                .greskaPodeljka6("0").greskaPodeljka7("0").greskaPodeljka8("0").ndg2("±1.2").ndr1("1.2")
                .odstupanje6("-0.80").odstupanje7("-0.80").odstupanje8("-0.80").odstupanje9("-0.90").odstupanje10("-0.90").ndg3("±2,6")
                .greska9("31-32").greska10("32-33").greska11("41-42").greska12("42-43").greska13("55-56").greska14("56-57").greska15("85-86").greska16("86-87")
                .greskaPodeljka9("-0.05").greskaPodeljka10("0").greskaPodeljka11("0").greskaPodeljka12("0").greskaPodeljka13("-0.05").greskaPodeljka14("0").greskaPodeljka15("-0.05").greskaPodeljka16("-0.05")
                .ndg4("±1,2").ndr2("1,2").brojMernogLenjira("650462").brojMerneLupe("2049").skinutiZigovi("77;23").postavljeniZigovi("25;131").meriloIspunjavaZahteve(true)
                .etalonirao("Nikola Danilovic").odobrio("Maja Aleksic").odobreno(true).komentar2("Komentar2").build());

        masinaZaMerenjeRepository.save(MasinaZaMerenje.builder().brojZapisnika("39/23-K2").datum(new Date()).build());

        mernaLetvaRepository.save(MernaLetva.builder().brojZapisnika("45/23-K1").datum(new Date()).build());

        mernaTrakaSaViskomRepository.save(MernaTrakaSaViskom.builder().brojZapisnika("46/23-K1").datum(new Date()).build());

        metriZaTekstilRepository.save(MetriZaTekstil.builder().brojZapisnika("47/23-K1").datum(new Date()).build());

        slozivoMeriloRepository.save(SlozivoMerilo.builder().brojZapisnika("47/23-K1").datum(new Date()).build());

    }
}
