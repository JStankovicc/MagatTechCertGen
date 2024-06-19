package com.magattech.certGen;

import com.magattech.certGen.model.*;
import com.magattech.certGen.model.enums.OpremaType;
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

    private final BrojZapisnikaRepository brojZapisnikaRepository;

    @Autowired
    private DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, VrstaKontrolisanjaRepository vrstaKontrolisanjaRepository, KompanijaRepository kompanijaRepository, ProizvodjacRepository proizvodjacRepository, OpremaRepository opremaRepository, JednodelnoMeriloRepository jednodelnoMeriloRepository, MasinaZaMerenjeRepository masinaZaMerenjeRepository, MernaLetvaRepository mernaLetvaRepository, MernaTrakaSaViskomRepository mernaTrakaSaViskomRepository, MetriZaTekstilRepository metriZaTekstilRepository, SlozivoMeriloRepository slozivoMeriloRepository, BrojZapisnikaRepository brojZapisnikaRepository){
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
        this.brojZapisnikaRepository = brojZapisnikaRepository;
    }

    @PostConstruct
    public void addDummyData(){
//        User adminUser = User.builder()
//                .firstName("Admin")
//                .lastName("Adminovic")
//                .email("admin@gmail.com")
//                .password(passwordEncoder.encode("123"))
//                .role(Role.ADMIN)
//                .build();
//        userRepository.save(adminUser);
//
//        User regularUser = User.builder()
//                .firstName("User")
//                .lastName("Regularevic")
//                .email("user@gmail.com")
//                .password(passwordEncoder.encode("123"))
//                .role(Role.USER)
//                .build();
//        userRepository.save(regularUser);
//
//        VrstaKontrolisanja vrstaKontrolisanja = VrstaKontrolisanja.builder().description("Редовно (периодично)").build();
//        vrstaKontrolisanjaRepository.save(vrstaKontrolisanja);
//
//        VrstaKontrolisanja vrstaKontrolisanja1 = VrstaKontrolisanja.builder().description("Ванредно").build();
//        vrstaKontrolisanjaRepository.save(vrstaKontrolisanja1);

//        Kompanija kompanija = Kompanija.builder().name("Kompanija 1").build();
//        kompanijaRepository.save(kompanija);
//        Kompanija kompanija1 = Kompanija.builder().name("Kompanija 2").build();
//        kompanijaRepository.save(kompanija1);
//
//        proizvodjacRepository.save(Proizvodjac.builder().name("Proizvodjac 1").build());
//
//       proizvodjacRepository.save(Proizvodjac.builder().name("Proizvodjac 2").build());

//        opremaRepository.save(Oprema.builder().tip(OpremaType.MERNA_LUPA).serBrEtalona("SerBrMerneLupe").date(new Date()).build());
//        opremaRepository.save(Oprema.builder().tip(OpremaType.MERNI_LENJIR).serBrEtalona("SerBrMernogLenjira").date(new Date()).build());
//        opremaRepository.save(Oprema.builder().tip(OpremaType.POMICNO_MERILO).serBrEtalona("SerBrPomicnogMerila").date(new Date()).build());

//        jednodelnoMeriloRepository.save(JednodelnoMerilo.builder().brojZapisnika("47/23-K1").datum(new Date()).vrstaKontrolisanja("Ванредно").podnosilacZahteva("Kompanija koja je podnela zahtev").korisnik("Kompanija koja je korisnik")
//                .serijskiBroj("ser000").identifikacioniBroj("id000").proizvodjac("Proizvodjac").oznakaTipa("TIP1").sluzbenaOznakaTipa("TIP S1").meriloJeIspravno(true)
//                .merniOpseg("0 mm do 1 mm").najmanjiPodeljak("1 cm").klasaTacnosti("III").temperatura("19.1").vlaznostVazduha("43,9")
//                .napomena("Napomena").odstupanje1("-0,80").odstupanje2("0").odstupanje3("-0,90").odstupanje4("-0,90").odstupanje5("-1").ndg1("±2,6")
//                .greska1("22-23").greska2("23-24").greska3("45-46").greska4("46-47").greska5("62-63").greska6("63-64").greska7("78-79").greska8("79-80")
//                .greskaPodeljka1("+0.05").greskaPodeljka2("0").greskaPodeljka3("-0.10").greskaPodeljka4("0").greskaPodeljka5("-0.10").greskaPodeljka5("0")
//                .greskaPodeljka6("0").greskaPodeljka7("0").greskaPodeljka8("0").ndg2("±1.2").ndr1("1.2")
//                .odstupanje6("-0.80").odstupanje7("-0.80").odstupanje8("-0.80").odstupanje9("-0.90").odstupanje10("-0.90").ndg3("±2,6")
//                .greska9("31-32").greska10("32-33").greska11("41-42").greska12("42-43").greska13("55-56").greska14("56-57").greska15("85-86").greska16("86-87")
//                .greskaPodeljka9("-0.05").greskaPodeljka10("0").greskaPodeljka11("0").greskaPodeljka12("0").greskaPodeljka13("-0.05").greskaPodeljka14("0").greskaPodeljka15("-0.05").greskaPodeljka16("-0.05")
//                .ndg4("±1,2").ndr2("1,2").brojMernogLenjira("650462").brojMerneLupe("2049").skinutiZigovi("77;23").postavljeniZigovi("25;131").meriloIspunjavaZahteve(true)
//                .etalonirao("Nikola Danilovic").odobrio("Maja Aleksic").odobreno(false).komentar2("Komentar2").pravilnik("PRAVILNIK").propisaniZahtevi("Propisani zahtevi").build());
//
//        mernaLetvaRepository.save(MernaLetva.builder().brojZapisnika("47/23-K1").datum(new Date()).vrstaKontrolisanja("Ванредно").podnosilacZahteva("Podnosilac zahteva doo").korisnik("Korisnik doo").serijskiBroj("311/2015").identifikacioniBroj("/").proizvodjac("PROIZVODJAC").oznakaTipa("TIP")
//                .sluzbenaOznakaTipa("Sl TIP").najmanjiPodeljak("1 mm").merniOpseg("0 mm do 3 mm").klasaTacnosti("II").temperatura("19,4").vlaznostVazduha("44,9").meriloJeIspravno(true)
//                .odstupanje1("-0,35").odstupanje2("-0,40").odstupanje3("-0,40").odstupanje4("-0,40").odstupanje5("-0,45").odstupanje6("-0,55").ndg1("1,4").ndg2("1,4")
//                .ndg3("1,8").ndg4("1,8").ndg5("2,2").ndg6("2,2").greska1("660-661").greska2("661-662").greska3("1530-1531").greska4("1531-1532").greska5("2250-2251").greska6("2251-2252")
//                .greska7("2863-2864").greska8("2864-2865").greskaPodeljka1("-0,05").greskaPodeljka2("-0,10").greskaPodeljka3("-0,05").greskaPodeljka4("-0,05").greskaPodeljka5("0").greskaPodeljka6("-0,10").greskaPodeljka7("-0,10").greskaPodeljka8("-0,05")
//                .ndg7("±0,4").ndr1("0,4").brojMernogLenjira("650462").brojMerneLupe("2049").skinutiZigovi("63;22").postavljeniZigovi("25;131").napomena("NAPOMENA").meriloIspunjavaZahteve(true).komentar2("Komentar broj 2").etalonirao("Nikola Danilovic").odobrio("Maja Aleksic").odobreno(false).pravilnik("PRAVILNIK").build());
//
//
//        mernaTrakaSaViskomRepository.save(MernaTrakaSaViskom.builder().brojZapisnika("47/23-K1").datum(new Date()).vrstaKontrolisanja("Prvo").podnosilacZahteva("Neki doo").korisnik("Drugi doo").serijskiBroj("46/23").identifikacioniBroj("/")
//                .proizvodjac("Neka firma").oznakaTipa("Tip").sluzbenaOznakaTipa("SL TIP").merniOpseg("0 mm do 10 m").najmanjiPodeljak("1 mm"). klasaTacnosti("II").temperatura("19,4").vlaznostVazduha("49,4")
//                .meriloJeIspravno(true).napomena("NAPOMENA").odstupanje1("0").odstupanje2("-0,10").odstupanje3("-0,15").odstupanje4("-0,30").odstupanje5("-0,40").odstupanje6("-0,50").odstupanje7("-0,55")
//                .odstupanje8("-0,60").odstupanje9("-0,70").odstupanje10("-0,80").odstupanje11("-0,90").ndg1("0.6").ndg2("0.6").ndg3("0.7").ndg4("0.9").ndg5("1,1").ndg6("1,3").ndg7("1,5").ndg8("1,7").ndg9("1,9").ndg10("2,1").ndg11("2,3")
//                .greska1("660-661").greska2("661-662").greska3("1350-1351").greska4("1351-1352").greska5("5800-5801").greska6("5801-5802").greska7("8560-8561").greska8("8561-8562")
//                .greskaPodeljka1("-0,05").greskaPodeljka2("0").greskaPodeljka3("0").greskaPodeljka4("0").greskaPodeljka5("-0,05").greskaPodeljka6("0").greskaPodeljka7("0").greskaPodeljka8("-0,05").ndg12("±0,2").ndr1("0,2")
//                .brojMernogLenjira("650462").brojMerneLupe("2049").skinutiZigovi("/").postavljeniZigovi("25;131").meriloIspunjavaZahteve(true).komentar2("Komentar 2").etalonirao("Nikola Danilovic").odobrio("Maja Aleksic").odobreno(false).pravilnik("PRAVILNIK").build());
//
//        masinaZaMerenjeRepository.save(MasinaZaMerenje.builder().brojZapisnika("47/23-K2").datum(new Date()).vrstaKontrolisanja("Periodicno (redovno)").podnosilacZahteva("Elektronapon doo").korisnik("Elektronapon doo").serijskiBroj("/").identifikacioniBroj("85434")
//                .proizvodjac("Minion").oznakaTipa("VM-1").sluzbenaOznakaTipa("D-8-17").merniOpseg("50 m do 99999,9 m").najmanjiPodeljak("0,1 m").klasaTacnosti("/").temperatura("19,5").vlaznostVazduha("45,5").meriloJeIspravno(true).napomena("/")
//                .merenje1("157,70").merenje2("157,75").merenje3("157,80").proveraIspravnogVodjenja("nijePrimenljivo").proveraIspravnostiPokaznogUredjaja(true).duzinaUzorka("50").debljinaUzorka("0,0065").pokazivanjeMasine("50").odstupanjeOdPraveVrednostiDuzine("0,045").relativnaGreskaIzmereneDuzine("0,09").ndg1("0,5")
//                .brojMernogLenjira("197").brojPomicnogMerila("711").skinutiZigovi("00202158 (23)").meriloIspunjavaZahteve(true).komentar2("Komentar 2").etalonirao("Milos Belic").odobrio("Maja Aleksic").odobreno(false).pravilnik("PRAVILNIK").build());
//
//
//        slozivoMeriloRepository.save(SlozivoMerilo.builder().brojZapisnika("47/23-K2").datum(new Date()).vrstaKontrolisanja("Periodicno (redovno)").podnosilacZahteva("LIBELA").korisnik("DELTA").serijskiBroj("/").identifikacioniBroj("200").proizvodjac("S-VAGA").oznakaTipa("RS").sluzbenaOznakaTipa("D-1-5")
//                .napomena("/").merniOpseg("0 mm do 1 m").najmanjiPodeljak("1 cm").klasaTacnosti("III").temperatura("19,5").vlaznostVazduha("42,8").meriloJeIspravno(true).odstupanje1("-0,80").odstupanje2("-0,90").odstupanje3("-0,90").odstupanje4("-0,90").odstupanje5("-1,00")
//                .ndg1("±2,6").greska1("22-23").greska2("23-24").greska3("45-46").greska4("46-47").greska5("62-63").greska6("63-64").greska7("78-79").greska8("79-80").greskaPodeljka1("+0,05").greskaPodeljka2("0").greskaPodeljka3("-0,10").greskaPodeljka4("0").greskaPodeljka5("-0,10").greskaPodeljka6("0").greskaPodeljka7("0").greskaPodeljka8("0")
//                .ndg2("±1,2").ndr1("1,2").odstupanje6("-0,80").odstupanje7("-0,80").odstupanje8("-0,80").odstupanje9("-0,90").odstupanje10("-0,90").ndg3("±2,6")
//                .greska9("31-32").greska10("32-33").greska11("41-42").greska12("42-43").greska13("55-56").greska14("56-57").greska15("85-86").greska16("86-87").greskaPodeljka9("-0,05").greskaPodeljka10("0").greskaPodeljka11("0").greskaPodeljka12("0").greskaPodeljka13("-0,05").greskaPodeljka14("0").greskaPodeljka15("-0,05").greskaPodeljka16("-0,05")
//                .ndg4("±1,2").ndr2("1,2").brojMernogLenjira("650462").brojMerneLupe("2049").skinutiZigovi("77;23").postavljeniZigovi("25;131").meriloIspunjavaZahteve(true).komentar2("/").etalonirao("Nikola Danilovic").odobreno(false).odobrio("Maja Aleksic").pravilnik("PRAVILNIK").build());
//
//
//        metriZaTekstilRepository.save(MetriZaTekstil.builder().brojZapisnika("47/23-K3").datum(new Date()).vrstaKontrolisanja("Periodicno (redovno)").podnosilacZahteva("LIBELA").korisnik("DELTA").serijskiBroj("/").identifikacioniBroj("200").proizvodjac("S-VAGA").oznakaTipa("RS").sluzbenaOznakaTipa("D-1-5")
//                .napomena("/").merniOpseg("0 mm do 1 m").najmanjiPodeljak("1 cm").klasaTacnosti("III").temperatura("19,5").vlaznostVazduha("42,8").meriloJeIspravno(true).odstupanje1("-0,80").odstupanje2("-0,90").odstupanje3("-0,90").odstupanje4("-0,90").odstupanje5("-1,00")
//                .ndg1("±2,6").greska1("22-23").greska2("23-24").greska3("45-46").greska4("46-47").greska5("62-63").greska6("63-64").greska7("78-79").greska8("79-80").greskaPodeljka1("+0,05").greskaPodeljka2("0").greskaPodeljka3("-0,10").greskaPodeljka4("0").greskaPodeljka5("-0,10").greskaPodeljka6("0").greskaPodeljka7("0").greskaPodeljka8("0")
//                .ndg2("±1,2").ndr1("1,2").odstupanje6("-0,80").odstupanje7("-0,80").odstupanje8("-0,80").odstupanje9("-0,90").odstupanje10("-0,90").ndg3("±2,6")
//                .greska9("31-32").greska10("32-33").greska11("41-42").greska12("42-43").greska13("55-56").greska14("56-57").greska15("85-86").greska16("86-87").greskaPodeljka9("-0,05").greskaPodeljka10("0").greskaPodeljka11("0").greskaPodeljka12("0").greskaPodeljka13("-0,05").greskaPodeljka14("0").greskaPodeljka15("-0,05").greskaPodeljka16("-0,05")
//                .ndg4("±1,2").ndr2("1,2").brojMernogLenjira("650462").brojMerneLupe("2049").skinutiZigovi("77;23").postavljeniZigovi("25;131").meriloIspunjavaZahteve(true).komentar2("/").etalonirao("Nikola Danilovic").odobreno(false).odobrio("Maja Aleksic").pravilnik("PRAVILNIK").build());
//



//        brojZapisnikaRepository.save(BrojZapisnika.builder().broj(1).godina(24).build());

    }
}
