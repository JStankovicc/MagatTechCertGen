package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;
import com.magattech.certGen.repository.JednodelnoMeriloRepository;
import com.magattech.certGen.repository.KompanijaRepository;
import com.magattech.certGen.repository.VrstaKontrolisanjaRepository;
import com.magattech.certGen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JednodelnoMeriloServiceImpl implements JednodelnoMeriloService {
    private final JednodelnoMeriloRepository jednodelnoMeriloRepository;
    private final VrstaKontrolisanjaService vrstaKontrolisanjaService;
    private final KompanijaService kompanijaService;
    private final ProizvodjacService proizvodjacService;
    private final UserService userService;
    private final JwtService jwtService;
    private final OpremaService opremaService;

    @Override
    public void add(JednodelnoMeriloRequest request) {

        User user = userService.findByEmail(request.getZapisnikUneo());
        User user2 = userService.findByEmail(request.getZapisnikOdobrio());

        Proizvodjac proizvodjac = proizvodjacService.getByName(request.getProizvodjac());
        if(proizvodjac.getName() == null){
            proizvodjacService.save(Proizvodjac.builder().name(request.getProizvodjac()).build());
        }
        Kompanija kompanija = kompanijaService.getByName(request.getKorisnik());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getKorisnik()).build());
        }
        kompanija = kompanijaService.getByName(request.getPodnosilacZahteva());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getPodnosilacZahteva()).build());
        }

        String ispravnost = request.getMeriloJeIspravno();
        boolean ispravnostBool = true;
        if(Objects.equals(ispravnost, "NE")) ispravnostBool = false;

        String ispunjavaZahteve = request.getMeriloIspunjavaZahteve();
        boolean ispunjavaZahteveBool = true;
        if(Objects.equals(ispunjavaZahteve, "NE")) {
            ispunjavaZahteveBool = false;
        }

        Date datum = request.getDatum();
        if(datum == null){
            datum = new Date();
        }

        JednodelnoMerilo jednodelnoMerilo = JednodelnoMerilo.builder().brojZapisnika(request.getBrojZapisnika())
                .vrstaKontrolisanja(request.getVrstaKontrolisanja())
                .podnosilacZahteva(request.getPodnosilacZahteva())
                .korisnik(request.getKorisnik())
                .serijskiBroj(request.getSerijskiBroj())
                .identifikacioniBroj(request.getIdentifikacioniBroj())
                .proizvodjac(request.getProizvodjac())
                .oznakaTipa(request.getOznakaTipa())
                .sluzbenaOznakaTipa(request.getSluzbenaOznakaTipa())
                .merniOpseg(request.getMerniOpseg())
                .najmanjiPodeljak(request.getNajmanjiPodeljak())
                .klasaTacnosti(request.getKlasaTacnosti())
                .temperatura(request.getTemperatura())
                .vlaznostVazduha(request.getVlaznostVazduha())
                .meriloJeIspravno(ispravnostBool)
                .napomena(request.getNapomena())
                .odstupanje1(request.getOdstupanje1())
                .odstupanje2(request.getOdstupanje2())
                .odstupanje3(request.getOdstupanje3())
                .odstupanje4(request.getOdstupanje4())
                .odstupanje5(request.getOdstupanje5())
                .ndg1(request.getNdg1())
                .greska1(request.getGreska1a() + "-" + request.getGreska1b())
                .greska2(request.getGreska2a() + "-" + request.getGreska2b())
                .greska3(request.getGreska3a() + "-" + request.getGreska3b())
                .greska4(request.getGreska4a() + "-" + request.getGreska4b())
                .greska5(request.getGreska5a() + "-" + request.getGreska5b())
                .greska6(request.getGreska6a() + "-" + request.getGreska6b())
                .greska7(request.getGreska7a() + "-" + request.getGreska7b())
                .greska8(request.getGreska8a() + "-" + request.getGreska8b())
                .greskaPodeljka1(request.getGreskaPodeljka1())
                .greskaPodeljka2(request.getGreskaPodeljka2())
                .greskaPodeljka3(request.getGreskaPodeljka3())
                .greskaPodeljka4(request.getGreskaPodeljka4())
                .greskaPodeljka5(request.getGreskaPodeljka5())
                .greskaPodeljka6(request.getGreskaPodeljka6())
                .greskaPodeljka7(request.getGreskaPodeljka7())
                .greskaPodeljka8(request.getGreskaPodeljka8())
                .ndg2(request.getNdg2())
                .ndr1(request.getNdr1())
                .odstupanje6(request.getOdstupanje6())
                .odstupanje7(request.getOdstupanje7())
                .odstupanje8(request.getOdstupanje8())
                .odstupanje9(request.getOdstupanje9())
                .odstupanje10(request.getOdstupanje10())
                .ndg3(request.getNdg3())
                .greska9(request.getGreska9a() + "-" + request.getGreska9b())
                .greska10(request.getGreska10a() + "-" + request.getGreska10b())
                .greska11(request.getGreska11a() + "-" + request.getGreska11b())
                .greska12(request.getGreska12a() + "-" + request.getGreska12b())
                .greska13(request.getGreska13a() + "-" + request.getGreska13b())
                .greska14(request.getGreska14a() + "-" + request.getGreska14b())
                .greska15(request.getGreska15a() + "-" + request.getGreska15b())
                .greska16(request.getGreska16a() + "-" + request.getGreska16b())
                .greskaPodeljka9(request.getGreskaPodeljka9())
                .greskaPodeljka10(request.getGreskaPodeljka10())
                .greskaPodeljka11(request.getGreskaPodeljka11())
                .greskaPodeljka12(request.getGreskaPodeljka12())
                .greskaPodeljka13(request.getGreskaPodeljka13())
                .greskaPodeljka14(request.getGreskaPodeljka14())
                .greskaPodeljka15(request.getGreskaPodeljka15())
                .greskaPodeljka16(request.getGreskaPodeljka16())
                .ndg4(request.getNdg4())
                .ndr2(request.getNdr2())
                .brojMernogLenjira(opremaService.findLatestByTip(OpremaType.MERNI_LENJIR).getSerBrEtalona())
                .brojMerneLupe(opremaService.findLatestByTip(OpremaType.MERNA_LUPA).getSerBrEtalona())
                .skinutiZigovi(request.getSkinutiZigovi())
                .postavljeniZigovi(request.getPostavljeniZigovi())
                .meriloIspunjavaZahteve(ispunjavaZahteveBool)
                .komentar2(request.getKomentar2())
                .datum(datum)
                .etalonirao(user.getFirstName() + " " + user.getLastName())
                .odobrio(user2.getFirstName() + " " + user2.getLastName())
                .odobreno(true)
                .unit1(request.getUnit1())
                .unit2(request.getUnit2())
                .propisaniZahtevi(request.getPropisaniZahtevi())
                .pravilnik(request.getPropisaniZahtevi())
                .build();

                jednodelnoMeriloRepository.save(jednodelnoMerilo);

    }

    @Override
    public void update(String id, JednodelnoMeriloRequest request){

        User user = userService.findByEmail(request.getZapisnikUneo());
        User user2 = userService.findByEmail(request.getZapisnikOdobrio());

        Proizvodjac proizvodjac = proizvodjacService.getByName(request.getProizvodjac());
        if(proizvodjac.getName() == null){
            proizvodjacService.save(Proizvodjac.builder().name(request.getProizvodjac()).build());
        }
        Kompanija kompanija = kompanijaService.getByName(request.getKorisnik());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getKorisnik()).build());
        }
        kompanija = kompanijaService.getByName(request.getPodnosilacZahteva());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getPodnosilacZahteva()).build());
        }

        String ispravnost = request.getMeriloJeIspravno();
        boolean ispravnostBool = true;
        if(Objects.equals(ispravnost, "NE")) ispravnostBool = false;

        String ispunjavaZahteve = request.getMeriloIspunjavaZahteve();
        boolean ispunjavaZahteveBool = true;
        if(Objects.equals(ispunjavaZahteve, "NE")) ispunjavaZahteveBool = false;

        Date datum = request.getDatum();
        if(datum == null){
            datum = new Date();
        }

        JednodelnoMerilo jednodelnoMerilo = getByBrojZapisnika(id);
        JednodelnoMerilo newJednodelnoMerilo = JednodelnoMerilo.builder().id(jednodelnoMerilo.getId())
                .brojZapisnika(request.getBrojZapisnika())
                .vrstaKontrolisanja(request.getVrstaKontrolisanja())
                .podnosilacZahteva(request.getPodnosilacZahteva())
                .korisnik(request.getKorisnik())
                .serijskiBroj(request.getSerijskiBroj())
                .identifikacioniBroj(request.getIdentifikacioniBroj())
                .proizvodjac(request.getProizvodjac())
                .oznakaTipa(request.getOznakaTipa())
                .sluzbenaOznakaTipa(request.getSluzbenaOznakaTipa())
                .merniOpseg(request.getMerniOpseg())
                .najmanjiPodeljak(request.getNajmanjiPodeljak())
                .klasaTacnosti(request.getKlasaTacnosti())
                .temperatura(request.getTemperatura())
                .vlaznostVazduha(request.getVlaznostVazduha())
                .meriloJeIspravno(ispravnostBool)
                .napomena(request.getNapomena())
                .odstupanje1(request.getOdstupanje1())
                .odstupanje2(request.getOdstupanje2())
                .odstupanje3(request.getOdstupanje3())
                .odstupanje4(request.getOdstupanje4())
                .odstupanje5(request.getOdstupanje5())
                .ndg1(request.getNdg1())
                .greska1(request.getGreska1a() + "-" + request.getGreska1b())
                .greska2(request.getGreska2a() + "-" + request.getGreska2b())
                .greska3(request.getGreska3a() + "-" + request.getGreska3b())
                .greska4(request.getGreska4a() + "-" + request.getGreska4b())
                .greska5(request.getGreska5a() + "-" + request.getGreska5b())
                .greska6(request.getGreska6a() + "-" + request.getGreska6b())
                .greska7(request.getGreska7a() + "-" + request.getGreska7b())
                .greska8(request.getGreska8a() + "-" + request.getGreska8b())
                .greskaPodeljka1(request.getGreskaPodeljka1())
                .greskaPodeljka2(request.getGreskaPodeljka2())
                .greskaPodeljka3(request.getGreskaPodeljka3())
                .greskaPodeljka4(request.getGreskaPodeljka4())
                .greskaPodeljka5(request.getGreskaPodeljka5())
                .greskaPodeljka6(request.getGreskaPodeljka6())
                .greskaPodeljka7(request.getGreskaPodeljka7())
                .greskaPodeljka8(request.getGreskaPodeljka8())
                .ndg2(request.getNdg2())
                .ndr1(request.getNdr1())
                .odstupanje6(request.getOdstupanje6())
                .odstupanje7(request.getOdstupanje7())
                .odstupanje8(request.getOdstupanje8())
                .odstupanje9(request.getOdstupanje9())
                .odstupanje10(request.getOdstupanje10())
                .ndg3(request.getNdg3())
                .greska9(request.getGreska9a() + "-" + request.getGreska9b())
                .greska10(request.getGreska10a() + "-" + request.getGreska10b())
                .greska11(request.getGreska11a() + "-" + request.getGreska11b())
                .greska12(request.getGreska12a() + "-" + request.getGreska12b())
                .greska13(request.getGreska13a() + "-" + request.getGreska13b())
                .greska14(request.getGreska14a() + "-" + request.getGreska14b())
                .greska15(request.getGreska15a() + "-" + request.getGreska15b())
                .greska16(request.getGreska16a() + "-" + request.getGreska16b())
                .greskaPodeljka9(request.getGreskaPodeljka9())
                .greskaPodeljka10(request.getGreskaPodeljka10())
                .greskaPodeljka11(request.getGreskaPodeljka11())
                .greskaPodeljka12(request.getGreskaPodeljka12())
                .greskaPodeljka13(request.getGreskaPodeljka13())
                .greskaPodeljka14(request.getGreskaPodeljka14())
                .greskaPodeljka15(request.getGreskaPodeljka15())
                .greskaPodeljka16(request.getGreskaPodeljka16())
                .ndg4(request.getNdg4())
                .ndr2(request.getNdr2())
                .brojMernogLenjira(opremaService.findLatestByTip(OpremaType.MERNI_LENJIR).getSerBrEtalona())
                .brojMerneLupe(opremaService.findLatestByTip(OpremaType.MERNA_LUPA).getSerBrEtalona())
                .skinutiZigovi(request.getSkinutiZigovi())
                .postavljeniZigovi(request.getPostavljeniZigovi())
                .meriloIspunjavaZahteve(ispunjavaZahteveBool)
                .komentar2(request.getKomentar2())
                .datum(datum)
                .etalonirao(user.getFirstName() + " " + user.getLastName())
                .odobrio(user2.getFirstName() + " " + user2.getLastName())
                .odobreno(true)
                .unit1(request.getUnit1())
                .unit2(request.getUnit2())
                .propisaniZahtevi(request.getPropisaniZahtevi())
                .pravilnik(request.getPropisaniZahtevi())
                .build();

        jednodelnoMeriloRepository.save(newJednodelnoMerilo);
    }

    @Override
    public JednodelnoMerilo getById(int id) {
        return jednodelnoMeriloRepository.findById(id).orElse(JednodelnoMerilo.builder().brojZapisnika(null).build());
    }

    @Override
    public JednodelnoMerilo getByBrojZapisnika(String brojZapisnika) {
        return jednodelnoMeriloRepository.findByBrojZapisnika(brojZapisnika).orElse(JednodelnoMerilo.builder().brojZapisnika(null).build());
    }

    @Override
    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest) {

        String username = jwtService.extractUserName(odobrenjeRequest.getToken());
        UserDetails user = userService.userDetailsService().loadUserByUsername(username);

        JednodelnoMerilo jednodelnoMerilo = this.getByBrojZapisnika(odobrenjeRequest.getBrojZapisnika());
        jednodelnoMerilo.setOdobreno(true);
        jednodelnoMerilo.setOdobrio(user.getUsername());

        //da li je UserDetails ili User??
    }

    @Override
    public List<JednodelnoMerilo> getAll() {
        return jednodelnoMeriloRepository.findAll();
    }

    @Override
    public List<JednodelnoMerilo> getNeoverena() {
        return jednodelnoMeriloRepository.findAllByOdobreno(false);
    }

    @Override
    public void odobri(String brojZapisnika) {
        JednodelnoMerilo jednodelnoMerilo = this.getByBrojZapisnika(brojZapisnika);
        jednodelnoMerilo.setOdobreno(true);
        jednodelnoMeriloRepository.save(jednodelnoMerilo);
    }
}
