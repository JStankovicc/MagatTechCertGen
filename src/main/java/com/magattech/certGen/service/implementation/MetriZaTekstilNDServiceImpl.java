package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MetriZaTekstilND;
import com.magattech.certGen.repository.MetriZaTekstilNDRepository;
import com.magattech.certGen.service.MetriZaTekstilNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetriZaTekstilNDServiceImpl implements MetriZaTekstilNDService {
    private final MetriZaTekstilNDRepository metriZaTekstilNDRepository;


    @Override
    public MetriZaTekstilND getMetriZaTekstilND() {
        return metriZaTekstilNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMetriZaTekstilND(MetriZaTekstilND metriZaTekstilND) {
        MetriZaTekstilND metriZaTekstilND1 = getMetriZaTekstilND();

        if(metriZaTekstilND1 == null) {
            metriZaTekstilND1 = new MetriZaTekstilND();
        }

        metriZaTekstilND1.setNdg1(metriZaTekstilND.getNdg1());
        metriZaTekstilND1.setNdg2(metriZaTekstilND.getNdg2());
        metriZaTekstilND1.setNdg3(metriZaTekstilND.getNdg3());
        metriZaTekstilND1.setNdg4(metriZaTekstilND.getNdg4());
        metriZaTekstilND1.setNdr1(metriZaTekstilND.getNdr1());
        metriZaTekstilND1.setNdr2(metriZaTekstilND.getNdr2());

        metriZaTekstilNDRepository.saveAndFlush(metriZaTekstilND1);
    }
}
