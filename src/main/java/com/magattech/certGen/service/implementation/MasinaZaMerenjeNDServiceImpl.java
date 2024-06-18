package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.MasinaZaMerenjeND;
import com.magattech.certGen.repository.MasinaZaMerenjeNDRepository;
import com.magattech.certGen.service.MasinaZaMerenjeNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasinaZaMerenjeNDServiceImpl implements MasinaZaMerenjeNDService {
    private final MasinaZaMerenjeNDRepository masinaZaMerenjeNDRepository;


    @Override
    public MasinaZaMerenjeND getMasinaZaMerenjeND() {
        return masinaZaMerenjeNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateMasinaZaMerenjeND(MasinaZaMerenjeND masinaZaMerenjeND) {
        MasinaZaMerenjeND masinaZaMerenjeND1 = getMasinaZaMerenjeND();

        if (masinaZaMerenjeND1 == null){
            masinaZaMerenjeND1 = new MasinaZaMerenjeND();
        }

        masinaZaMerenjeND1.setNdg1(masinaZaMerenjeND.getNdg1());

        masinaZaMerenjeNDRepository.saveAndFlush(masinaZaMerenjeND1);
    }
}
