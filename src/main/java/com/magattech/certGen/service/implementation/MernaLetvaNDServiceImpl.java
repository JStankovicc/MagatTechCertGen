package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MernaLetvaND;
import com.magattech.certGen.repository.MernaLetvaNDRepository;
import com.magattech.certGen.service.MernaLetvaNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MernaLetvaNDServiceImpl implements MernaLetvaNDService {
    private final MernaLetvaNDRepository mernaLetvaNDRepository;
    @Override
    public MernaLetvaND getMernaLetvaND() {
        return mernaLetvaNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMernaLetvaND(MernaLetvaND mernaLetvaND) {
        MernaLetvaND mernaLetvaND1 = getMernaLetvaND();

        if(mernaLetvaND1 == null) {
            mernaLetvaND1 = new MernaLetvaND();
        }

        mernaLetvaND1.setNdg1(mernaLetvaND.getNdg1());
        mernaLetvaND1.setNdg2(mernaLetvaND.getNdg2());
        mernaLetvaND1.setNdg3(mernaLetvaND.getNdg3());
        mernaLetvaND1.setNdg4(mernaLetvaND.getNdg4());
        mernaLetvaND1.setNdg5(mernaLetvaND.getNdg5());
        mernaLetvaND1.setNdg6(mernaLetvaND.getNdg6());
        mernaLetvaND1.setNdg7(mernaLetvaND.getNdg7());
        mernaLetvaND1.setNdr1(mernaLetvaND.getNdr1());

        mernaLetvaNDRepository.saveAndFlush(mernaLetvaND1);
    }
}
