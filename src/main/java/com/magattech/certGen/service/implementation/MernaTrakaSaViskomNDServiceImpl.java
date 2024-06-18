package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MernaTrakaSaViskomND;
import com.magattech.certGen.repository.MernaLetvaNDRepository;
import com.magattech.certGen.repository.MernaTrakaSaViskomNDRepository;
import com.magattech.certGen.service.MernaTrakaSaViskomNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MernaTrakaSaViskomNDServiceImpl implements MernaTrakaSaViskomNDService {
    private final MernaTrakaSaViskomNDRepository mernaTrakaSaViskomNDRepository;
    @Override
    public MernaTrakaSaViskomND getMernaTrakaSaViskomND() {
        return mernaTrakaSaViskomNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMernaTrakaSaViskomND(MernaTrakaSaViskomND mernaTrakaSaViskomND) {
        MernaTrakaSaViskomND mernaTrakaSaViskomND1 = getMernaTrakaSaViskomND();

        if (mernaTrakaSaViskomND1 == null) {
            mernaTrakaSaViskomND1 = new MernaTrakaSaViskomND();
        }

        mernaTrakaSaViskomND1.setNdg1(mernaTrakaSaViskomND.getNdg1());
        mernaTrakaSaViskomND1.setNdg2(mernaTrakaSaViskomND.getNdg2());
        mernaTrakaSaViskomND1.setNdg3(mernaTrakaSaViskomND.getNdg3());
        mernaTrakaSaViskomND1.setNdg4(mernaTrakaSaViskomND.getNdg4());
        mernaTrakaSaViskomND1.setNdg5(mernaTrakaSaViskomND.getNdg5());
        mernaTrakaSaViskomND1.setNdg6(mernaTrakaSaViskomND.getNdg6());
        mernaTrakaSaViskomND1.setNdg7(mernaTrakaSaViskomND.getNdg7());
        mernaTrakaSaViskomND1.setNdg8(mernaTrakaSaViskomND.getNdg8());
        mernaTrakaSaViskomND1.setNdg9(mernaTrakaSaViskomND.getNdg9());
        mernaTrakaSaViskomND1.setNdg10(mernaTrakaSaViskomND.getNdg10());
        mernaTrakaSaViskomND1.setNdg11(mernaTrakaSaViskomND.getNdg11());
        mernaTrakaSaViskomND1.setNdg12(mernaTrakaSaViskomND.getNdg12());
        mernaTrakaSaViskomND1.setNdr1(mernaTrakaSaViskomND.getNdr1());

        mernaTrakaSaViskomNDRepository.saveAndFlush(mernaTrakaSaViskomND1);
    }
}
