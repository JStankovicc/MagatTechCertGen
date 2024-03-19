package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.included.Oprema;
import com.magattech.certGen.repository.OpremaRepository;
import com.magattech.certGen.service.OpremaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpremaServiceImpl implements OpremaService {
    private final OpremaRepository opremaRepository;
    @Override
    public List<Oprema> getAll() {
        return opremaRepository.findAll();
    }

    @Override
    public Oprema getById(int id) {
        return opremaRepository.findById(id).orElse(Oprema.builder().serBrEtalona(null).build());
    }

    @Override
    public Oprema getByName(String name) {
        return opremaRepository.findByName(name).orElse(Oprema.builder().serBrEtalona(null).build());
    }

    @Override
    public Oprema getByEtalon(String serBrEtalona) {
        return opremaRepository.findBySerBrEtalona(serBrEtalona).orElse(Oprema.builder().serBrEtalona(null).build());
    }

    @Override
    public void save(Oprema oprema) {
        opremaRepository.save(oprema);
    }
}
