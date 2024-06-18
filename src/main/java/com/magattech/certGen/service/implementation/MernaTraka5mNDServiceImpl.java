package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MernaTraka5mND;
import com.magattech.certGen.repository.MernaTraka5mNDRepository;
import com.magattech.certGen.repository.MernaTraka5mRepository;
import com.magattech.certGen.service.MernaTraka5mNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MernaTraka5mNDServiceImpl implements MernaTraka5mNDService {

    private final MernaTraka5mNDRepository mernaTraka5mNDRepository;


    @Override
    public MernaTraka5mND getMernaTraka5mND() {
        return mernaTraka5mNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMernaTraka5mND(MernaTraka5mND mernaTraka5mND) {
        MernaTraka5mND mernaTraka5mND1 = getMernaTraka5mND();

        if (mernaTraka5mND1 == null) {
            mernaTraka5mND1 = new MernaTraka5mND();
        }

        mernaTraka5mND1.setNdg1(mernaTraka5mND.getNdg1());
        mernaTraka5mND1.setNdg2(mernaTraka5mND.getNdg2());
        mernaTraka5mND1.setNdg3(mernaTraka5mND.getNdg3());
        mernaTraka5mND1.setNdg4(mernaTraka5mND.getNdg4());
        mernaTraka5mND1.setNdg5(mernaTraka5mND.getNdg5());
        mernaTraka5mND1.setNdg6(mernaTraka5mND.getNdg6());
        mernaTraka5mND1.setNdg7(mernaTraka5mND.getNdg7());
        mernaTraka5mND1.setNdr1(mernaTraka5mND.getNdr1());

        mernaTraka5mNDRepository.saveAndFlush(mernaTraka5mND1);
    }
}
