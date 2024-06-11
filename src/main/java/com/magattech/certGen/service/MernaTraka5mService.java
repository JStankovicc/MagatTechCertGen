package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MernaTraka5m;
import com.magattech.certGen.model.request.MernaTraka5mRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

import java.util.List;

public interface MernaTraka5mService {
    public void add(MernaTraka5mRequest mernaTrakaSaViskomRequest);
    public MernaTraka5m getById(int id);

    public MernaTraka5m getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

    List<MernaTraka5m> getAll();

    List<MernaTraka5m> getAllNeoverena();

    void odobri(String brojZapisnika);

    void update(String id, MernaTraka5mRequest request);
}
