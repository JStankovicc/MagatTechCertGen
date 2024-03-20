package com.magattech.certGen.service;

import com.magattech.certGen.model.included.VrstaKontrolisanja;

import java.util.List;

public interface VrstaKontrolisanjaService {
    public List<VrstaKontrolisanja> getAll();
    public VrstaKontrolisanja getVrstaKontrolisanjaById(int id);
    public VrstaKontrolisanja getVrstaKontrolisanjaByName(String name);
    public void addVrstaKontrolisanja(String name);
}
