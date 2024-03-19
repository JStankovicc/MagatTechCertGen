package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.VrstaKontrolisanja;
import com.magattech.certGen.repository.VrstaKontrolisanjaRepository;
import com.magattech.certGen.service.VrstaKontrolisanjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VrstaKontrolisanjaServiceImpl implements VrstaKontrolisanjaService {

    private final VrstaKontrolisanjaRepository vrstaKontrolisanjaRepository;
    @Override
    public VrstaKontrolisanja getVrstaKontrolisanjaById(int id) {
        return vrstaKontrolisanjaRepository.findById(id).orElse(VrstaKontrolisanja.builder().description(null).build());
    }

    @Override
    public VrstaKontrolisanja getVrstaKontrolisanjaByName(String name) {
        return vrstaKontrolisanjaRepository.findByDescription(name).orElse(VrstaKontrolisanja.builder().description(null).build());
    }

    @Override
    public void addVrstaKontrolisanja(String name) {
        VrstaKontrolisanja vrstaKontrolisanja = vrstaKontrolisanjaRepository.save(VrstaKontrolisanja.builder().description(name).build());
    }
}
