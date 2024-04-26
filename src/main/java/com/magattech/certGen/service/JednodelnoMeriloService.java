package com.magattech.certGen.service;


import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

import java.util.List;

public interface JednodelnoMeriloService {
    public void add(JednodelnoMeriloRequest jednodelnoMeriloRequest);
    public JednodelnoMerilo getById(int id);

    public JednodelnoMerilo getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

    List<JednodelnoMerilo> getAll();

    List<JednodelnoMerilo> getNeoverena();

    void odobri(String brojZapisnika);
}
