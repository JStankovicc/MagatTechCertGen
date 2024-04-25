package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;

import java.util.List;

public interface MasinaZaMerenjeService {

    List<MasinaZaMerenje> getAll();

    public void add(MasinaZaMerenjeRequest masinaZaMerenjeRequest);

    public MasinaZaMerenje getByBrojZapisnika(String brojZapisnika);
}
