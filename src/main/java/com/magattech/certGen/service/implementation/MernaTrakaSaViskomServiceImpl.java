package com.magattech.certGen.service.implementation;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class MernaTrakaSaViskomServiceImpl implements MernaTrakaSaViskomService {

    private final MernaTrakaSaViskomRepository mernaTrakaSaViskomRepository;
    private final VrstaKontrolisanjaService vrstaKontrolisanjaService;
    private final KompanijaService kompanijaService;
    private final ProizvodjacService proizvodjacService;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void add(MernaTrakaSaViskomRequest request) {
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

        MernaTrakaSaViskom mernaTrakaSaViskom = MernaTrakaSaViskom.builder()
                .brojZapisnika(request.getBrojZapisnika())
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
                .ndg12(request.getNdg12())
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

        mernaTrakaSaViskomRepository.save(mernaTrakaSaViskom);
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
    public List<MernaTrakaSaViskom> getAll() {
        return mernaTrakaSaViskomRepository.findAll();
    }
}
