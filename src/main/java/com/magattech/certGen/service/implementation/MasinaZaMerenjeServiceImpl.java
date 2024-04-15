package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
import com.magattech.certGen.repository.MasinaZaMerenjeRepository;
import com.magattech.certGen.service.MasinaZaMerenjeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasinaZaMerenjeServiceImpl implements MasinaZaMerenjeService {

    private final MasinaZaMerenjeRepository masinaZaMerenjeRepository;

    @Override
    public List<MasinaZaMerenje> getAll() {
        return masinaZaMerenjeRepository.findAll();
    }

    @Override
    public void add(MasinaZaMerenjeRequest masinaZaMerenjeRequest) {

    }
}