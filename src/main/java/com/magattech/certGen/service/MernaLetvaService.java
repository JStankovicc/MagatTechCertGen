package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.model.request.OdobrenjeRequest;

import java.util.List;

public interface MernaLetvaService {

    public void add(MernaLetvaRequest mernaLetvaRequest);
    public MernaLetva getById(int id);

    public MernaLetva getByBrojZapisnika(String brojZapisnika);

    public void addOdobrenje(OdobrenjeRequest odobrenjeRequest);

    List<MernaLetva> getAll();

    List<MernaLetva> findAllNeoverena();

    void odobri(String brojZapisnika);

    void update(String newId, MernaLetvaRequest mernaLetvaRequest);

    List<MernaLetva> getAllByBrojSeta(String brojSeta);
}
