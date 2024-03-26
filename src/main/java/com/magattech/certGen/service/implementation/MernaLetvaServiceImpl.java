package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;
import com.magattech.certGen.repository.MernaLetvaRepository;
import com.magattech.certGen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MernaLetvaServiceImpl implements MernaLetvaService {
    private final MernaLetvaRepository mernaLetvaRepository;
    private final VrstaKontrolisanjaService vrstaKontrolisanjaService;
    private final KompanijaService kompanijaService;
    private final ProizvodjacService proizvodjacService;
    private final UserService userService;
    private final JwtService jwtService;
    @Override
    public void add(MernaLetvaRequest request) {

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

        MernaLetva mernaLetva = MernaLetva.builder().brojZapisnika(request.getBrojZapisnika())
                .podnosilacZahteva(podnosilacZahteva.getId())
                .vrstaKontrolisanja(vrstaKontrolisanja.getId())
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
                .meriloIspunjavaZahteve(ispravnostBool)
                .napomena(request.getNapomena())
                .odstupanje1(request.getOdstupanje1())
                .odstupanje2(request.getOdstupanje2())
                .odstupanje3(request.getOdstupanje3())
                .odstupanje4(request.getOdstupanje4())
                .odstupanje5(request.getOdstupanje5())
                .odstupanje6(request.getOdstupanje6())
                .ndg1(request.getNdg1())
                .ndg2(request.getNdg2())
                .ndg3(request.getNdg3())
                .ndg4(request.getNdg4())
                .ndg5(request.getNdg5())
                .ndg6(request.getNdg6())
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
                .ndg7(request.getNdg7())
                .ndr1(request.getNdr1())
                .brojMernogLenjira(request.getBrojMernogLenjira())
                .brojMerneLupe(request.getBrojMerneLupe())
                .skinutiZigovi(request.getSkinutiZigovi())
                .postavljeniZigovi(request.getPostavljeniZigovi())
                .meriloIspunjavaZahteve(ispunjavaZahteveBool)
                .komentar2(request.getKomentar2())
                .datum(request.getDatum())
                .odobreno(false)
                .build();

        mernaLetvaRepository.save(mernaLetva);
    }

    @Override
    public MernaLetva getById(int id) {
        return mernaLetvaRepository.findById(id).orElse(MernaLetva.builder().brojZapisnika(null).build());
    }

    @Override
    public MernaLetva getByBrojZapisnika(String brojZapisnika) {
        return mernaLetvaRepository.findByBrojZapisnika(brojZapisnika).orElse(MernaLetva.builder().brojZapisnika(null).build());
    }

    @Override
    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest) {

    }

    @Override
    public List<MernaLetva> getAll() {
        return mernaLetvaRepository.findAll();
    }
}
