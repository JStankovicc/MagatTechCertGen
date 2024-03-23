package com.magattech.certGen.service;


import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

public interface JednodelnoMeriloService {
    public void add(JednodelnoMeriloRequest jednodelnoMeriloRequest);
    public JednodelnoMerilo getById(int id);

    public JednodelnoMerilo getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

}
