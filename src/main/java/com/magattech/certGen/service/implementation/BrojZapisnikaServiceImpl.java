package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.BrojZapisnika;
import com.magattech.certGen.repository.*;
import com.magattech.certGen.service.BrojZapisnikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrojZapisnikaServiceImpl implements BrojZapisnikaService {
    private final BrojZapisnikaRepository brojZapisnikaRepository;

    private final JednodelnoMeriloRepository jednodelnoMeriloRepository;
    private final MernaLetvaRepository mernaLetvaRepository;
    private final MernaTrakaSaViskomRepository mernaTrakaSaViskomRepository;
    private final MasinaZaMerenjeRepository masinaZaMerenjeRepository;
    private final MetriZaTekstilRepository metriZaTekstilRepository;
    private final SlozivoMeriloRepository slozivoMeriloRepository;

    @Override
    public List<String> getAll(){
        List<BrojZapisnika> brojZapisnika = brojZapisnikaRepository.findAll();
        List<String> obradjeni = obradiZapisnike(brojZapisnika);
        return obradjeni;
    }

    @Override
    public List<String> getThisYear(){
        LocalDateTime dateTime = LocalDateTime.now();
        String year = String.valueOf(dateTime.getYear());
        String lastTwoDigits = year.substring(year.length() - 2);
        List<BrojZapisnika> brojZapisnikas = brojZapisnikaRepository.findByGodina(Integer.valueOf(lastTwoDigits));
        List<String> obradjeni = obradiZapisnike(brojZapisnikas);
        return obradjeni;
    }

    @Override
    public List<String> getWithoutThisYear(){
        LocalDateTime dateTime = LocalDateTime.now();
        String year = String.valueOf(dateTime.getYear());
        String lastTwoDigits = year.substring(year.length() - 2);
        List<BrojZapisnika> brojZapisnikas = brojZapisnikaRepository.findByGodinaNot(Integer.valueOf(lastTwoDigits));
        List<String> obradjeni = obradiZapisnike(brojZapisnikas);
        return obradjeni;
    }

    @Override
    public String getAktuelniBrojZapisnika() {
        BrojZapisnika brojZapisnika = brojZapisnikaRepository.getAktuelniBrojZapisnika();
        String broj = brojZapisnika.toString();

        List<String> stringovi = new ArrayList<>();

        addToNonNull(stringovi, jednodelnoMeriloRepository.findBiggestBrojZapisnika(broj));
        addToNonNull(stringovi, mernaLetvaRepository.findBiggestBrojZapisnika(broj));
        addToNonNull(stringovi, mernaTrakaSaViskomRepository.findBiggestBrojZapisnika(broj));
        addToNonNull(stringovi, masinaZaMerenjeRepository.findBiggestBrojZapisnika(broj));
        addToNonNull(stringovi, metriZaTekstilRepository.findBiggestBrojZapisnika(broj));
        addToNonNull(stringovi, slozivoMeriloRepository.findBiggestBrojZapisnika(broj));

        if (stringovi.isEmpty()) {
            return broj+1;
        }

        int najveciOstatak = pronadjiNajveciOstatak(stringovi, broj);
        najveciOstatak++;

        return broj + najveciOstatak;
    }

    @Override
    public void updateZapisnik() {
        BrojZapisnika brojZapisnika = brojZapisnikaRepository.getAktuelniBrojZapisnika();
        int year = LocalDateTime.now().getYear() % 100;
        int number = 1;
        if(year == brojZapisnika.getGodina()){
            number = brojZapisnika.getBroj() + 1;
        }
        BrojZapisnika newBroj = BrojZapisnika.builder().broj(number).godina(year).build();
        brojZapisnikaRepository.save(newBroj);
    }

    @Override
    public void checkAndUpdateZapisnik(){
        String aktuelniBroj = getAktuelniBrojZapisnika();
        if(!aktuelniBroj.endsWith("1")){
            updateZapisnik();
        }
    }


    private void addToNonNull(List<String> list, String element) {
        if (element != null) {
            list.add(element);
        }
    }

    private int pronadjiNajveciOstatak(List<String> stringovi, String broj) {
        int najveciOstatakInt = -1;
        for (String str : stringovi) {
            String ostatakStr = str.substring(broj.length());
            int ostatakInt = Integer.parseInt(ostatakStr.replaceAll("\\D+", ""));
            najveciOstatakInt = Math.max(najveciOstatakInt, ostatakInt);
        }
        return najveciOstatakInt;
    }

    private List<String> obradiZapisnike(List<BrojZapisnika> brojZapisnika){
        List<String> obradjeni = new ArrayList<>();
        for(BrojZapisnika brojZapisnika1 : brojZapisnika){
            String bZ = brojZapisnika1.getBroj() + "/" + brojZapisnika1.getGodina();
            obradjeni.add(bZ);
        }
        return obradjeni;
    }
}
