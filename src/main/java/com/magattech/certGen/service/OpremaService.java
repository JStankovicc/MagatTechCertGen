package com.magattech.certGen.service;

import com.magattech.certGen.model.included.Oprema;

import java.util.List;

public interface OpremaService {
    public List<Oprema> getAll();
    public Oprema getById(int id);
    public Oprema getByName(String name);
    public Oprema getByEtalon(String serBrEtalona);
    public void save(Oprema oprema);
}
