package com.magattech.certGen.service;

import com.magattech.certGen.model.included.Oprema;
import com.magattech.certGen.model.request.OpremaRequest;

import java.util.List;

public interface OpremaService {
    public List<Oprema> findAll();
    public Oprema getById(int id);
    public Oprema getByName(String name);
    public Oprema getByEtalon(String serBrEtalona);
    public void addOprema(OpremaRequest opremaRequest);

    public void deleteOpremaById(String id);
}
