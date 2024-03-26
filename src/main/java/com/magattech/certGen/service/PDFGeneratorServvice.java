package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.JednodelnoMerilo;

public interface PDFGeneratorServvice {
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo);
}
