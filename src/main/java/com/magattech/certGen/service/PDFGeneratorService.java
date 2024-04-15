package com.magattech.certGen.service;

import com.magattech.certGen.model.merila.*;

public interface PDFGeneratorService {
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo);

    public byte[] generateMernaLetva(MernaLetva mernaLetva);

    public byte[] generateMernaTrakaSaViskom(MernaTrakaSaViskom mernaTrakaSaViskom);

    public byte[] generateMasinaZaMerenje(MasinaZaMerenje masinaZaMerenje);
    public byte[] generateSlozivoMerilo(SlozivoMerilo slozivoMerilo);
    public byte[] generateMetriZaTekstil(MetriZaTekstil metriZaTekstil);
}
