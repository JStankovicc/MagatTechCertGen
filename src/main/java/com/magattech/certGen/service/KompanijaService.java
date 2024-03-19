package com.magattech.certGen.service;

import com.magattech.certGen.model.Kompanija;

import java.util.List;

public interface KompanijaService {
    public List<Kompanija> getAll();
    public Kompanija getById(int id);
    public Kompanija getByName(String name);
    public void save(Kompanija kompanija);
}
