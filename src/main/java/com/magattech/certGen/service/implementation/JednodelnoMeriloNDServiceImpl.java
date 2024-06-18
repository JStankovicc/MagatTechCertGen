package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.additional.JednodelnoMeriloND;
import com.magattech.certGen.repository.JednodelnoMeriloNDRepository;
import com.magattech.certGen.service.JednodelnoMeriloNDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JednodelnoMeriloNDServiceImpl implements JednodelnoMeriloNDService {
    private final JednodelnoMeriloNDRepository jednodelnoMeriloNDRepository;
    @Override
    public JednodelnoMeriloND getJednodelnoMeriloND() {
        return jednodelnoMeriloNDRepository.findById(1).orElse(null);
    }

    @Override
    public void updateJednodelnoMeriloND(JednodelnoMeriloND jednodelnoMeriloND) {
        JednodelnoMeriloND jednodelnoMeriloND1 = getJednodelnoMeriloND();

        if (jednodelnoMeriloND1 == null) {
            jednodelnoMeriloND1 = new JednodelnoMeriloND();
        }

        jednodelnoMeriloND1.setNdg1(jednodelnoMeriloND.getNdg1());
        jednodelnoMeriloND1.setNdg2(jednodelnoMeriloND.getNdg2());
        jednodelnoMeriloND1.setNdg3(jednodelnoMeriloND.getNdg3());
        jednodelnoMeriloND1.setNdg4(jednodelnoMeriloND.getNdg4());
        jednodelnoMeriloND1.setNdr1(jednodelnoMeriloND.getNdr1());
        jednodelnoMeriloND1.setNdr2(jednodelnoMeriloND.getNdr2());
        jednodelnoMeriloNDRepository.saveAndFlush(jednodelnoMeriloND);
    }
}
