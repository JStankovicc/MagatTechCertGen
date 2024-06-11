package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MernaTraka25m;
import com.magattech.certGen.model.request.MernaTraka25mRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

import java.util.List;

public interface MernaTraka25mService {
    public void add(MernaTraka25mRequest mernaTrakaSaViskomRequest);
    public MernaTraka25m getById(int id);

    public MernaTraka25m getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

    List<MernaTraka25m> getAll();

    List<MernaTraka25m> getAllNeoverena();

    void odobri(String brojZapisnika);

    void update(String id, MernaTraka25mRequest request);
}
