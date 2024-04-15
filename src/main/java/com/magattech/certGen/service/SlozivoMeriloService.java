package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.MetriZaTekstilRequest;
import com.magattech.certGen.model.request.SlozivoMeriloRequest;

import java.util.List;

public interface SlozivoMeriloService {
    List<SlozivoMerilo> getAll();

    public void add(SlozivoMeriloRequest slozivoMeriloRequest);
}