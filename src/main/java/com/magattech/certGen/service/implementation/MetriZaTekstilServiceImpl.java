package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.request.MetriZaTekstilRequest;
import com.magattech.certGen.repository.MetriZaTekstilRepository;
import com.magattech.certGen.service.MetriZaTekstilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetriZaTekstilServiceImpl implements MetriZaTekstilService {
    private final MetriZaTekstilRepository metriZaTekstilRepository;
    @Override
    public List<MetriZaTekstil> getAll() {
        return metriZaTekstilRepository.findAll();
    }

    @Override
    public void add(MetriZaTekstilRequest metriZaTekstilRequest) {

    }
}
