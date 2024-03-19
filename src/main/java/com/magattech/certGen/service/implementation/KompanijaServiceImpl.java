package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.Kompanija;
import com.magattech.certGen.repository.KompanijaRepository;
import com.magattech.certGen.service.KompanijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KompanijaServiceImpl implements KompanijaService {
    private final KompanijaRepository kompanijaRepository;
    @Override
    public List<Kompanija> getAll() {
        return kompanijaRepository.findAll();
    }

    @Override
    public Kompanija getById(int id) {
        return kompanijaRepository.findById(id).orElse(Kompanija.builder().name(null).build());
    }

    @Override
    public Kompanija getByName(String name) {
        return kompanijaRepository.findByName(name).orElse(Kompanija.builder().name(null).build());
    }

    @Override
    public void save(Kompanija kompanija) {
        kompanijaRepository.save(kompanija);
    }
}
