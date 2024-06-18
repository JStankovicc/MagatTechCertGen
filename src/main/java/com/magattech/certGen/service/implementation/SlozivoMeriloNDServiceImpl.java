package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.SlozivoMeriloND;
import com.magattech.certGen.repository.SlozivoMeriloNDRepository;
import com.magattech.certGen.service.SlozivoMeriloNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlozivoMeriloNDServiceImpl implements SlozivoMeriloNDService {
    private final SlozivoMeriloNDRepository slozivoMeriloNDRepository;


    @Override
    public SlozivoMeriloND getSlozivoMeriloND() {
        return slozivoMeriloNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateSlozivoMeriloND(SlozivoMeriloND slozivoMeriloND) {
        SlozivoMeriloND slozivoMeriloND1 = getSlozivoMeriloND();

        if(slozivoMeriloND1 == null){
            slozivoMeriloND1 = new SlozivoMeriloND();
        }

        slozivoMeriloND1.setNdg1(slozivoMeriloND.getNdg1());
        slozivoMeriloND1.setNdg2(slozivoMeriloND.getNdg2());
        slozivoMeriloND1.setNdg3(slozivoMeriloND.getNdg3());
        slozivoMeriloND1.setNdg4(slozivoMeriloND.getNdg4());
        slozivoMeriloND1.setNdr1(slozivoMeriloND.getNdr1());
        slozivoMeriloND1.setNdr2(slozivoMeriloND.getNdr2());

        slozivoMeriloNDRepository.saveAndFlush(slozivoMeriloND1);
    }
}
