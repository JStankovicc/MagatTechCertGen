package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
import com.magattech.certGen.model.request.MetriZaTekstilRequest;

import java.util.List;

public interface MetriZaTekstilService {

    List<MetriZaTekstil> getAll();

    public void add(MetriZaTekstilRequest metriZaTekstilRequest);

    public MetriZaTekstil getByBrojZapisnika(String brojZapisnika);

    List<MetriZaTekstil> getAllNeoverena();

    void odobri(String brojZapisnika);

    void update(String newId, MetriZaTekstilRequest metriZaTekstilRequest);

    List<MetriZaTekstil> getAllByBrojSeta(String brojSeta);
}
