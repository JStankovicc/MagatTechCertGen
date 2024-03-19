package com.magattech.certGen.service;

import com.magattech.certGen.model.included.VrstaKontrolisanja;

public interface VrstaKontrolisanjaService {
    public VrstaKontrolisanja getVrstaKontrolisanjaById(int id);
    public VrstaKontrolisanja getVrstaKontrolisanjaByName(String name);
    public void addVrstaKontrolisanja(String name);
}
