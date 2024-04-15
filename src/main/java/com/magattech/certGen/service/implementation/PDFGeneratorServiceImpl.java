package com.magattech.certGen.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.magattech.certGen.model.merila.*;
import com.magattech.certGen.service.PDFGeneratorService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PDFGeneratorServiceImpl implements PDFGeneratorService {

    @Override
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "47-23-K1 Zapisnik o kontrolisanju-overavanju(Jednodelno merilo dužine1).docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateMernaLetva(MernaLetva mernaLetva) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "45-23-К1 Zapisnik o kontrolisanju-overavanju (merna letva).docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateMernaTrakaSaViskom(MernaTrakaSaViskom mernaTrakaSaViskom) {

            String staticResourcePath = "src/main/resources/static/";
            String wordFilePath = staticResourcePath + "46-23-K1 Zapisnik o kontrolisanju-overavanju (merna traka s viskom).docx";

            File wordFile = new File(wordFilePath);

            if (!wordFile.exists()) {
                System.err.println("Word file not found: " + wordFilePath);
                return null;
            }

            try {
                return Files.readAllBytes(wordFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public byte[] generateMasinaZaMerenje(MasinaZaMerenje masinaZaMerenje) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "39-23-K1 Zapisnik o kontrolisanju-overavanju (mašina za merenje žice i k....docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateSlozivoMerilo(SlozivoMerilo slozivoMerilo) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "slozivo merilo.docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateMetriZaTekstil(MetriZaTekstil metriZaTekstil) {
        String staticResourcePath = "src/main/resources/static/";
        String wordFilePath = staticResourcePath + "metri za tekstil.docx";

        File wordFile = new File(wordFilePath);

        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        try {
            return Files.readAllBytes(wordFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
