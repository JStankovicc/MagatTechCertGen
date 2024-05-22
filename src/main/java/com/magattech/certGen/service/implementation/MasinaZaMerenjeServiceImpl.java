package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
import com.magattech.certGen.repository.MasinaZaMerenjeRepository;
import com.magattech.certGen.service.KompanijaService;
import com.magattech.certGen.service.MasinaZaMerenjeService;
import com.magattech.certGen.service.OpremaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasinaZaMerenjeServiceImpl implements MasinaZaMerenjeService {

    private final MasinaZaMerenjeRepository masinaZaMerenjeRepository;
    private final OpremaService opremaService;
    private final KompanijaService kompanijaService;

    @Override
    public List<MasinaZaMerenje> getAll() {
        return masinaZaMerenjeRepository.findAll();
    }

    @Override
    public void add(MasinaZaMerenjeRequest request) {
        Kompanija kompanija = kompanijaService.getByName(request.getProizvodjac());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getProizvodjac()).build());
        }
        kompanija = kompanijaService.getByName(request.getKorisnik());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getKorisnik()).build());
        }
        kompanija = kompanijaService.getByName(request.getPodnosilacZahteva());
        if(kompanija.getName() == null){
            kompanijaService.save(Kompanija.builder().name(request.getPodnosilacZahteva()).build());
        }


        String ispravnost = request.getMeriloJeIspravno();
        boolean ispravnostBool = true;
        if(ispravnost == "NE") ispravnostBool = false;

        String ispravnostPokaznogUredjaja = request.getProveraIspravnostiPokaznogUredjaja();
        boolean ispravnostPokaznogUredjajaBool = true;
        if(ispravnostPokaznogUredjaja == "NE") ispravnostPokaznogUredjajaBool = false;

        String ispunjavaZahteve = request.getMeriloIspunjavaZahteve();
        boolean ispunjavaZahteveBool = true;
        if(ispunjavaZahteve == "NE") ispunjavaZahteveBool = false;

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
                .datum(request.getDatum())
                .etalonirao(request.getEtalonirao())
                .odobrio(request.getOdobrio())
                .odobreno(true)
                .unit1(request.getUnit1())
                .unit2(request.getUnit2())
                .propisaniZahtevi(request.getPropisaniZahtevi())
                .merniLenjir(request.getMerniLenjir())
                .mernaLupa(request.getMernaLupa())
                .pomicnoMerilo(request.getPomicnoMerilo())
                .pravilnik(request.getPropisaniZahtevi())
                .brojMernogLenjira(opremaService.findLatestByTip(OpremaType.MERNI_LENJIR).getSerBrEtalona())
                .brojPomicnogMerila(opremaService.findLatestByTip(OpremaType.POMICNO_MERILO).getSerBrEtalona())
                .build();

        masinaZaMerenjeRepository.save(masinaZaMerenje);
    }

    @Override
    public MasinaZaMerenje getByBrojZapisnika(String brojZapisnika) {
        return masinaZaMerenjeRepository.findByBrojZapisnika(brojZapisnika).orElse(MasinaZaMerenje.builder().brojZapisnika(null).build());
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
}
