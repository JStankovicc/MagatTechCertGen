package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MernaTraka25mND;
import com.magattech.certGen.model.additional.MernaTrakaSaViskom25mND;
import com.magattech.certGen.repository.MernaTraka25mNDRepository;
import com.magattech.certGen.service.MernaTraka25mNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MernaTraka25mNDServiceImpl implements MernaTraka25mNDService {

    private final MernaTraka25mNDRepository mernaTraka25mNDRepository;


    @Override
    public MernaTraka25mND getMernaTraka25mND() {
        return mernaTraka25mNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMernaTraka25mND(MernaTraka25mND m) {
        MernaTraka25mND mernaTraka25mND = getMernaTraka25mND();

        if (mernaTraka25mND == null) {
            mernaTraka25mND = new MernaTraka25mND();
        }

        mernaTraka25mND.setNdg1(m.getNdg1());
        mernaTraka25mND.setNdg2(m.getNdg2());
        mernaTraka25mND.setNdg3(m.getNdg3());
        mernaTraka25mND.setNdg4(m.getNdg4());
        mernaTraka25mND.setNdg5(m.getNdg5());
        mernaTraka25mND.setNdg6(m.getNdg6());
        mernaTraka25mND.setNdg7(m.getNdg7());
        mernaTraka25mND.setNdg8(m.getNdg8());
        mernaTraka25mND.setNdg9(m.getNdg9());
        mernaTraka25mND.setNdg10(m.getNdg10());
        mernaTraka25mND.setNdg11(m.getNdg11());
        mernaTraka25mND.setNdg12(m.getNdg12());
        mernaTraka25mND.setNdg13(m.getNdg13());
        mernaTraka25mND.setNdg14(m.getNdg14());
        mernaTraka25mND.setNdg15(m.getNdg15());
        mernaTraka25mND.setNdg16(m.getNdg16());
        mernaTraka25mND.setNdg17(m.getNdg17());
        mernaTraka25mND.setNdg18(m.getNdg18());
        mernaTraka25mND.setNdg19(m.getNdg19());
        mernaTraka25mND.setNdg20(m.getNdg20());
        mernaTraka25mND.setNdg21(m.getNdg21());
        mernaTraka25mND.setNdg22(m.getNdg22());
        mernaTraka25mND.setNdg23(m.getNdg23());
        mernaTraka25mND.setNdg24(m.getNdg24());
        mernaTraka25mND.setNdg25(m.getNdg25());
        mernaTraka25mND.setNdg26(m.getNdg26());
        mernaTraka25mND.setNdg27(m.getNdg27());
        mernaTraka25mND.setNdr1(m.getNdr1());

        mernaTraka25mNDRepository.saveAndFlush(mernaTraka25mND);
    }
}
