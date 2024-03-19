package com.magattech.certGen.service;

import com.magattech.certGen.model.included.Proizvodjac;

import java.util.List;

public interface ProizvodjacService {
    public List<Proizvodjac> getAll();
    public Proizvodjac getById(int id);
    public Proizvodjac getByName(String name);
    public void save(Proizvodjac proizvodjac);
}
