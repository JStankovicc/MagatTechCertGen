package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.BrojZapisnika;
import com.magattech.certGen.repository.*;
import com.magattech.certGen.service.BrojZapisnikaService;
import lombok.RequiredArgsConstructor;
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

}
