package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
import com.magattech.certGen.repository.MasinaZaMerenjeRepository;
import com.magattech.certGen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MasinaZaMerenjeServiceImpl implements MasinaZaMerenjeService {

    private final MasinaZaMerenjeRepository masinaZaMerenjeRepository;
    private final OpremaService opremaService;
    private final KompanijaService kompanijaService;
    private final UserService userService;
    private final ProizvodjacService proizvodjacService;

    @Override
    public List<MasinaZaMerenje> getAll() {
        return masinaZaMerenjeRepository.findAll();
    }

    @Override
    public void add(MasinaZaMerenjeRequest request) {
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

        String ispravnostPokaznogUredjaja = request.getProveraIspravnostiPokaznogUredjaja();
        boolean ispravnostPokaznogUredjajaBool = true;
        if(Objects.equals(ispravnostPokaznogUredjaja, "NE")) ispravnostPokaznogUredjajaBool = false;

        String ispunjavaZahteve = request.getMeriloIspunjavaZahteve();
        boolean ispunjavaZahteveBool = true;
        if(Objects.equals(ispunjavaZahteve, "NE")) ispunjavaZahteveBool = false;

        Date datum = request.getDatum();
        if(datum == null){
            datum = new Date();
        }

        MasinaZaMerenje masinaZaMerenje = MasinaZaMerenje.builder().brojZapisnika(request.getBrojZapisnika())
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
                .proveraIspravnogVodjenja(request.getProveraIspravnogVodjenja())
                .proveraIspravnostiPokaznogUredjaja(ispravnostPokaznogUredjajaBool)
                .merenje1(request.getMerenje1())
                .merenje2(request.getMerenje2())
                .merenje3(request.getMerenje3())
                .duzinaUzorka(request.getDuzinaUzorka())
                .debljinaUzorka(request.getDebljinaUzorka())
                .pokazivanjeMasine(request.getPokazivanjeMasine())
                .odstupanjeOdPraveVrednostiDuzine(request.getOdstupanjeOdPraveVrednostiDuzine())
                .relativnaGreskaIzmereneDuzine(request.getRelativnaGreskaIzmereneDuzine())
                .ndg1(request.getNdg1())
                .skinutiZigovi(request.getSkinutiZigovi())
                .postavljeniZigovi(request.getPostavljeniZigovi())
                .pravilnik(request.getPravilnik())
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
                .brojMernogLenjira(opremaService.findLatestByTip(OpremaType.MERNI_LENJIR).getSerBrEtalona())
                .brojPomicnogMerila(opremaService.findLatestByTip(OpremaType.POMICNO_MERILO).getSerBrEtalona())
                .razlogOdbijanja(request.getRazlogOdbijanja())
                .build();

        masinaZaMerenjeRepository.save(masinaZaMerenje);
    }

    @Override
    public MasinaZaMerenje getByBrojZapisnika(String brojZapisnika) {
        return masinaZaMerenjeRepository.findByBrojZapisnika(brojZapisnika).orElse(MasinaZaMerenje.builder().brojZapisnika(null).build());
    }

    @Override
    public List<MasinaZaMerenje> getAllByBrojSeta(String brojSeta){
        String formattedBrojSeta = "%" + brojSeta + "%";
        List<MasinaZaMerenje> masinaZaMerenjeList = masinaZaMerenjeRepository.findAllByBrojZapisnikaLike(formattedBrojSeta);
        return masinaZaMerenjeList;
    }

    @Override
    public List<MasinaZaMerenje> findAllNeoverena() {
        return masinaZaMerenjeRepository.findAllByOdobreno(false);
    }

    @Override
    public void odobri(String brojZapisnika) {
        MasinaZaMerenje masinaZaMerenje = this.getByBrojZapisnika(brojZapisnika);
        masinaZaMerenje.setOdobreno(true);
        masinaZaMerenjeRepository.save(masinaZaMerenje);
    }

    @Override
    public void update(String id, MasinaZaMerenjeRequest request) {
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

        String ispravnostPokaznogUredjaja = request.getProveraIspravnostiPokaznogUredjaja();
        boolean ispravnostPokaznogUredjajaBool = true;
        if(Objects.equals(ispravnostPokaznogUredjaja, "NE")) ispravnostPokaznogUredjajaBool = false;

        String ispunjavaZahteve = request.getMeriloIspunjavaZahteve();
        boolean ispunjavaZahteveBool = true;
        if(Objects.equals(ispunjavaZahteve, "NE")) ispunjavaZahteveBool = false;

        Date datum = request.getDatum();
        if(datum == null){
            datum = new Date();
        }

        MasinaZaMerenje masinaZaMerenje = getByBrojZapisnika(id);

        MasinaZaMerenje newMasinaZaMerenje = MasinaZaMerenje.builder()
                .id(masinaZaMerenje.getId())
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
                .proveraIspravnogVodjenja(request.getProveraIspravnogVodjenja())
                .proveraIspravnostiPokaznogUredjaja(ispravnostPokaznogUredjajaBool)
                .merenje1(request.getMerenje1())
                .merenje2(request.getMerenje2())
                .merenje3(request.getMerenje3())
                .duzinaUzorka(request.getDuzinaUzorka())
                .debljinaUzorka(request.getDebljinaUzorka())
                .pokazivanjeMasine(request.getPokazivanjeMasine())
                .odstupanjeOdPraveVrednostiDuzine(request.getOdstupanjeOdPraveVrednostiDuzine())
                .relativnaGreskaIzmereneDuzine(request.getRelativnaGreskaIzmereneDuzine())
                .ndg1(request.getNdg1())
                .skinutiZigovi(request.getSkinutiZigovi())
                .postavljeniZigovi(request.getPostavljeniZigovi())
                .pravilnik(request.getPravilnik())
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
                .brojMernogLenjira(opremaService.findLatestByTip(OpremaType.MERNI_LENJIR).getSerBrEtalona())
                .brojPomicnogMerila(opremaService.findLatestByTip(OpremaType.POMICNO_MERILO).getSerBrEtalona())
                .razlogOdbijanja(request.getRazlogOdbijanja())
                .build();

        masinaZaMerenjeRepository.save(newMasinaZaMerenje);
    }
}
