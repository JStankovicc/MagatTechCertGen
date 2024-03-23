package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.User;
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

@Service
@RequiredArgsConstructor
public class JednodelnoMeriloServiceImpl implements JednodelnoMeriloService {
    private final JednodelnoMeriloRepository jednodelnoMeriloRepository;
    private final VrstaKontrolisanjaService vrstaKontrolisanjaService;
    private final KompanijaService kompanijaService;
    private final ProizvodjacService proizvodjacService;
    private final UserService userService;
    private final JwtService jwtService;
    @Override
    public void add(JednodelnoMeriloRequest request) {

        VrstaKontrolisanja vrstaKontrolisanja = vrstaKontrolisanjaService.getVrstaKontrolisanjaByName(request.getVrstaKontrolisanja());
        Kompanija podnosilacZahteva = kompanijaService.getByName(request.getPodnosilacZahteva());
        Kompanija korisnik = kompanijaService.getByName(request.getKorisnik());
        Proizvodjac proizvodjac = proizvodjacService.getByName(request.getProizvodjac());

        String ispravnost = request.getMeriloJeIspravno();
        boolean ispravnostBool = true;
        if(ispravnost == "NE") ispravnostBool = false;

        String ispunjavaZahteve = request.getMeriloIspunjavaZahteve();
        boolean ispunjavaZahteveBool = true;
        if(ispunjavaZahteve == "NE") ispunjavaZahteveBool = false;

        //String etalonirao = jwtService.extractUserName(request.getToken());
        //UserDetails etaloniraoUser = userService.userDetailsService().loadUserByUsername(etalonirao);

        JednodelnoMerilo jednodelnoMerilo = JednodelnoMerilo.builder().brojZapisnika(request.getBrojZapisnika())
                .vrstaKontrolisanja(vrstaKontrolisanja.getId())
                .podnosilacZahteva(podnosilacZahteva.getId())
                .korisnik(korisnik.getId())
                .serijskiBroj(request.getSerijskiBroj())
                .identifikacioniBroj(request.getIdentifikacioniBroj())
                .proizvodjac(proizvodjac.getId())
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
                .brojMernogLenjira(request.getBrojMernogLenjira())
                .brojMerneLupe(request.getBrojMerneLupe())
                .skinutiZigovi(request.getSkinutiZigovi())
                .postavljeniZigovi(request.getPostavljeniZigovi())
                .meriloIspunjavaZahteve(ispunjavaZahteveBool)
                .komentar2(request.getKomentar2())
                .datum(request.getDatum())
                //.etalonirao(etaloniraoUser.getUsername())
                .odobreno(false)
                .build();

                jednodelnoMeriloRepository.save(jednodelnoMerilo);
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
}
