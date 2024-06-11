package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MernaTrakaSaViskom25m;
import com.magattech.certGen.model.request.MernaTrakaSaViskom25mRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

import java.util.List;

public interface MernaTrakaSaViskom25mService {
    public void add(MernaTrakaSaViskom25mRequest mernaTrakaSaViskomRequest);
    public MernaTrakaSaViskom25m getById(int id);

    public MernaTrakaSaViskom25m getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

    List<MernaTrakaSaViskom25m> getAll();

    List<MernaTrakaSaViskom25m> getAllNeoverena();

    void odobri(String brojZapisnika);

    void update(String id, MernaTrakaSaViskom25mRequest request);
}
