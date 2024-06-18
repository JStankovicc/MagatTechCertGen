package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.additional.MetriZaTekstilND;
import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.MetriZaTekstilRequest;
import com.magattech.certGen.repository.MetriZaTekstilRepository;
import com.magattech.certGen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MetriZaTekstilServiceImpl implements MetriZaTekstilService {
    private final MetriZaTekstilRepository metriZaTekstilRepository;
    private final OpremaService opremaService;
    private final KompanijaService kompanijaService;
    private final UserService userService;
    private final ProizvodjacService proizvodjacService;
    private final MetriZaTekstilNDService metriZaTekstilNDService;

    @Override
    public List<MetriZaTekstil> getAll() {
        return metriZaTekstilRepository.findAll();
    }

    @Override
    public void add(MetriZaTekstilRequest request) {
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

        MetriZaTekstil metriZaTekstil = MetriZaTekstil.builder().brojZapisnika(request.getBrojZapisnika())
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
                .unit3(request.getUnit3())
                .unit4(request.getUnit4())
                .propisaniZahtevi(request.getPropisaniZahtevi())
                .pravilnik(request.getPropisaniZahtevi())
                .razlogOdbijanja(request.getRazlogOdbijanja())
                .build();

        metriZaTekstilRepository.save(metriZaTekstil);

        MetriZaTekstilND metriZaTekstilND = metriZaTekstil.metriZaTekstilND();
        metriZaTekstilNDService.updateMetriZaTekstilND(metriZaTekstilND);

    }

    @Override
    public MetriZaTekstil getByBrojZapisnika(String brojZapisnika) {
        return metriZaTekstilRepository.findByBrojZapisnika(brojZapisnika).orElse(MetriZaTekstil.builder().brojZapisnika(null).build());
    }

    @Override
    public List<MetriZaTekstil> getAllByBrojSeta(String brojSeta){
        String formattedBrojSeta = "%" + brojSeta + "%";
        List<MetriZaTekstil> metriZaTekstils = metriZaTekstilRepository.findAllByBrojZapisnikaLike(formattedBrojSeta);
        return metriZaTekstils;
    }

    @Override
    public List<MetriZaTekstil> getAllNeoverena() {
        return metriZaTekstilRepository.findAllByOdobreno(false);
    }

    @Override
    public void odobri(String brojZapisnika) {
        MetriZaTekstil metriZaTekstil = this.getByBrojZapisnika(brojZapisnika);
        metriZaTekstil.setOdobreno(true);
        metriZaTekstilRepository.save(metriZaTekstil);
    }

    @Override
    public void update(String id, MetriZaTekstilRequest request) {
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

        MetriZaTekstil metriZaTekstil = getByBrojZapisnika(id);
        MetriZaTekstil newMetriZaTekstil = MetriZaTekstil.builder().id(metriZaTekstil.getId())
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
                .razlogOdbijanja(request.getRazlogOdbijanja())
                .build();

        metriZaTekstilRepository.save(newMetriZaTekstil);

        MetriZaTekstilND metriZaTekstilND = newMetriZaTekstil.metriZaTekstilND();
        metriZaTekstilNDService.updateMetriZaTekstilND(metriZaTekstilND);
    }
}
