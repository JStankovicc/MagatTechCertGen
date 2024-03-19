package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.repository.ProizvodjacRepository;
import com.magattech.certGen.service.ProizvodjacService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProizvodjacServiceImpl implements ProizvodjacService {
    private final ProizvodjacRepository proizvodjacRepository;
    @Override
    public List<Proizvodjac> getAll() {
        return proizvodjacRepository.findAll();
    }

    @Override
    public Proizvodjac getById(int id) {
        return proizvodjacRepository.findById(id).orElse(Proizvodjac.builder().name(null).build());
    }

    @Override
    public Proizvodjac getByName(String name) {
        return proizvodjacRepository.findByName(name).orElse(Proizvodjac.builder().name(null).build());
    }

    @Override
    public void save(Proizvodjac proizvodjac) {
        proizvodjacRepository.save(proizvodjac);
    }
}
