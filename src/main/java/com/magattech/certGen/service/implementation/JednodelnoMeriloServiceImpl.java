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

import java.util.List;

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

        System.out.println("OBRADA ZAHTEVA");

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
                .greska1(request.getGreska1())
                .greska2(request.getGreska2())
                .greska3(request.getGreska3())
                .greska4(request.getGreska4())
                .greska5(request.getGreska5())
                .greska6(request.getGreska6())
                .greska7(request.getGreska7())
                .greska8(request.getGreska8())
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
                .greska9(request.getGreska9())
                .greska10(request.getGreska10())
                .greska11(request.getGreska11())
                .greska12(request.getGreska12())
                .greska13(request.getGreska13())
                .greska14(request.getGreska14())
                .greska15(request.getGreska15())
                .greska16(request.getGreska16())
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

        System.out.println("SACUVAN ZAHTEV");
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
}
