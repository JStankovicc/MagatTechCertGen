package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.model.request.MernaTrakaSaViskomRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

import java.util.List;

public interface MernaTrakaSaViskomService {
    public void add(MernaTrakaSaViskomRequest mernaTrakaSaViskomRequest);
    public MernaTrakaSaViskom getById(int id);

    public MernaTrakaSaViskom getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

    List<MernaTrakaSaViskom> getAll();

    List<MernaTrakaSaViskom> getAllNeoverena();

    void odobri(String brojZapisnika);
}
