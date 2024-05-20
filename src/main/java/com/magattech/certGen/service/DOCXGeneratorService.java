package com.magattech.certGen.service;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.*;

public interface DOCXGeneratorService {
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo);
    public byte[] generateMernaLetva(MernaLetva mernaLetva);
    public byte[] generateMernaTrakaSaViskom(MernaTrakaSaViskom mernaTrakaSaViskom);
    public byte[] generateMasinaZaMerenje(MasinaZaMerenje masinaZaMerenje);
    public byte[] generateSlozivoMerilo(SlozivoMerilo slozivoMerilo);
    public byte[] generateMetriZaTekstil(MetriZaTekstil metriZaTekstil);
    public byte[] generateResenjeOOdbijanju(MeriloHelper meriloHelper);
    public byte[] generateSertifikatOKontrolisanju(MeriloHelper meriloHelper);
}
