package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.additional.MernaTrakaSaViskomND;
import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.request.MernaTrakaSaViskomRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;
import com.magattech.certGen.repository.MernaLetvaRepository;
import com.magattech.certGen.repository.MernaTrakaSaViskomRepository;
import com.magattech.certGen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MernaTrakaSaViskomServiceImpl implements MernaTrakaSaViskomService {

    private final MernaTrakaSaViskomRepository mernaTrakaSaViskomRepository;
    private final VrstaKontrolisanjaService vrstaKontrolisanjaService;
    private final KompanijaService kompanijaService;
    private final ProizvodjacService proizvodjacService;
    private final UserService userService;
    private final JwtService jwtService;
    private final OpremaService opremaService;
    private final MernaTrakaSaViskomNDService mernaTrakaSaViskomNDService;

    @Override
    public void add(MernaTrakaSaViskomRequest request) {
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

        MernaTrakaSaViskom mernaTrakaSaViskom = MernaTrakaSaViskom.builder()
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
                .odstupanje6(request.getOdstupanje6())
                .odstupanje7(request.getOdstupanje7())
                .odstupanje8(request.getOdstupanje8())
                .odstupanje9(request.getOdstupanje9())
                .odstupanje10(request.getOdstupanje10())
                .odstupanje11(request.getOdstupanje11())
                .ndg1(request.getNdg1())
                .ndg2(request.getNdg2())
                .ndg3(request.getNdg3())
                .ndg4(request.getNdg4())
                .ndg5(request.getNdg5())
                .ndg6(request.getNdg6())
                .ndg7(request.getNdg7())
                .ndg8(request.getNdg8())
                .ndg9(request.getNdg9())
                .ndg10(request.getNdg10())
                .ndg11(request.getNdg11())
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
                .ndg12(request.getNdg12())
                .ndr1(request.getNdr1())
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

        mernaTrakaSaViskomRepository.save(mernaTrakaSaViskom);

        MernaTrakaSaViskomND mernaTrakaSaViskomND = mernaTrakaSaViskom.getMernaTrakaSaViskomND();
        mernaTrakaSaViskomNDService.updateMernaTrakaSaViskomND(mernaTrakaSaViskomND);
    }

    @Override
    public MernaTrakaSaViskom getById(int id) {
        return mernaTrakaSaViskomRepository.findById(id).orElse(MernaTrakaSaViskom.builder().brojZapisnika(null).build());
    }

    @Override
    public MernaTrakaSaViskom getByBrojZapisnika(String brojZapisnika) {
        return mernaTrakaSaViskomRepository.findByBrojZapisnika(brojZapisnika).orElse(MernaTrakaSaViskom.builder().brojZapisnika(null).build());
    }

    @Override
    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest) {

    }

    @Override
    public List<MernaTrakaSaViskom> getAllByBrojSeta(String brojSeta){
        String formattedBrojSeta = "%" + brojSeta + "%";
        List<MernaTrakaSaViskom> mernaTrakaSaViskoms = mernaTrakaSaViskomRepository.findAllByBrojZapisnikaLike(formattedBrojSeta);
        return mernaTrakaSaViskoms;
    }

    @Override
    public List<MernaTrakaSaViskom> getAll() {
        return mernaTrakaSaViskomRepository.findAll();
    }

    @Override
    public List<MernaTrakaSaViskom> getAllNeoverena() {
        return mernaTrakaSaViskomRepository.findAllByOdobreno(false);
    }

    @Override
    public void odobri(String brojZapisnika) {
        MernaTrakaSaViskom mernaTrakaSaViskom = this.getByBrojZapisnika(brojZapisnika);
        mernaTrakaSaViskom.setOdobreno(true);
        mernaTrakaSaViskomRepository.save(mernaTrakaSaViskom);
    }

    @Override
    public void update(String id, MernaTrakaSaViskomRequest request) {
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

        MernaTrakaSaViskom mernaTrakaSaViskom = getByBrojZapisnika(id);

        MernaTrakaSaViskom newMernaTrakaSaViskom = MernaTrakaSaViskom.builder()
                .id(mernaTrakaSaViskom.getId())
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
                .odstupanje6(request.getOdstupanje6())
                .odstupanje7(request.getOdstupanje7())
                .odstupanje8(request.getOdstupanje8())
                .odstupanje9(request.getOdstupanje9())
                .odstupanje10(request.getOdstupanje10())
                .odstupanje11(request.getOdstupanje11())
                .ndg1(request.getNdg1())
                .ndg2(request.getNdg2())
                .ndg3(request.getNdg3())
                .ndg4(request.getNdg4())
                .ndg5(request.getNdg5())
                .ndg6(request.getNdg6())
                .ndg7(request.getNdg7())
                .ndg8(request.getNdg8())
                .ndg9(request.getNdg9())
                .ndg10(request.getNdg10())
                .ndg11(request.getNdg11())
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
                .ndg12(request.getNdg12())
                .ndr1(request.getNdr1())
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
                .razlogOdbijanja(request.getRazlogOdbijanja())
                .propisaniZahtevi(request.getPropisaniZahtevi())
                .pravilnik(request.getPropisaniZahtevi())
                .build();

        mernaTrakaSaViskomRepository.save(newMernaTrakaSaViskom);

        MernaTrakaSaViskomND mernaTrakaSaViskomND = newMernaTrakaSaViskom.getMernaTrakaSaViskomND();
        mernaTrakaSaViskomNDService.updateMernaTrakaSaViskomND(mernaTrakaSaViskomND);
    }
}
