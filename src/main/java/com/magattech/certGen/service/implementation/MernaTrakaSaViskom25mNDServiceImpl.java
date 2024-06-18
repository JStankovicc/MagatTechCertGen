package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MernaTrakaSaViskom25mND;
import com.magattech.certGen.repository.MernaTrakaSaViskom25mNDRepository;
import com.magattech.certGen.service.MernaTrakaSaViskom25mNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MernaTrakaSaViskom25mNDServiceImpl implements MernaTrakaSaViskom25mNDService {
    private final MernaTrakaSaViskom25mNDRepository mernaTrakaSaViskom25mNDRepository;

    @Override
    public MernaTrakaSaViskom25mND getMernaTrakaSaViskom25mND() {
        return mernaTrakaSaViskom25mNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMernaTrakaSaViskom25mND(MernaTrakaSaViskom25mND m) {
        MernaTrakaSaViskom25mND mernaTrakaSaViskom25mND = getMernaTrakaSaViskom25mND();

        if (mernaTrakaSaViskom25mND == null) {
            mernaTrakaSaViskom25mND = new MernaTrakaSaViskom25mND();
        }

        mernaTrakaSaViskom25mND.setNdg1(m.getNdg1());
        mernaTrakaSaViskom25mND.setNdg2(m.getNdg2());
        mernaTrakaSaViskom25mND.setNdg3(m.getNdg3());
        mernaTrakaSaViskom25mND.setNdg4(m.getNdg4());
        mernaTrakaSaViskom25mND.setNdg5(m.getNdg5());
        mernaTrakaSaViskom25mND.setNdg6(m.getNdg6());
        mernaTrakaSaViskom25mND.setNdg7(m.getNdg7());
        mernaTrakaSaViskom25mND.setNdg8(m.getNdg8());
        mernaTrakaSaViskom25mND.setNdg9(m.getNdg9());
        mernaTrakaSaViskom25mND.setNdg10(m.getNdg10());
        mernaTrakaSaViskom25mND.setNdg11(m.getNdg11());
        mernaTrakaSaViskom25mND.setNdg12(m.getNdg12());
        mernaTrakaSaViskom25mND.setNdg13(m.getNdg13());
        mernaTrakaSaViskom25mND.setNdg14(m.getNdg14());
        mernaTrakaSaViskom25mND.setNdg15(m.getNdg15());
        mernaTrakaSaViskom25mND.setNdg16(m.getNdg16());
        mernaTrakaSaViskom25mND.setNdg17(m.getNdg17());
        mernaTrakaSaViskom25mND.setNdg18(m.getNdg18());
        mernaTrakaSaViskom25mND.setNdg19(m.getNdg19());
        mernaTrakaSaViskom25mND.setNdg20(m.getNdg20());
        mernaTrakaSaViskom25mND.setNdg21(m.getNdg21());
        mernaTrakaSaViskom25mND.setNdg22(m.getNdg22());
        mernaTrakaSaViskom25mND.setNdg23(m.getNdg23());
        mernaTrakaSaViskom25mND.setNdg24(m.getNdg24());
        mernaTrakaSaViskom25mND.setNdg25(m.getNdg25());
        mernaTrakaSaViskom25mND.setNdg26(m.getNdg26());
        mernaTrakaSaViskom25mND.setNdg27(m.getNdg27());
        mernaTrakaSaViskom25mND.setNdr1(m.getNdr1());

        mernaTrakaSaViskom25mNDRepository.saveAndFlush(mernaTrakaSaViskom25mND);
    }
}
