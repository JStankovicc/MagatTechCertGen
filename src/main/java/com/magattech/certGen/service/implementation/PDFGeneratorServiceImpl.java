package com.magattech.certGen.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.service.PDFGeneratorService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PDFGeneratorServiceImpl implements PDFGeneratorService {

    @Override
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";

        String wordFilePath = desktopPath + "47-23-K1 Zapisnik o kontrolisanju-overavanju(Jednodelno merilo dužine1).docx";
        String pdfFilePath = desktopPath + "47-23-K1 Zapisnik o kontrolisanju-overavanju(Jednodelno merilo dužine1).pdf";

        File wordFile = new File(wordFilePath);
        if (!wordFile.exists()) {
            System.err.println("Word file not found: " + wordFilePath);
            return null;
        }

        File pdfFile = new File(pdfFilePath);
        if (pdfFile.exists() && !pdfFile.canWrite()) {
            System.err.println("No write permission for PDF file: " + pdfFilePath);
            return null;
        }

        try (FileInputStream wordInputStream = new FileInputStream(wordFile);
             FileOutputStream pdfOutputStream = new FileOutputStream(pdfFilePath)) {

            IConverter converter = LocalConverter.builder()
                    .baseFolder(new File("C:\\Users\\Jovan\\Desktop"))
                    .workerPool(20, 25, 2, TimeUnit.SECONDS)
                    .processTimeout(30, TimeUnit.SECONDS)
                    .build();

            Future<Boolean> conversion = converter
                    .convert(wordFile).as(DocumentType.MS_WORD)
                    .to(pdfFile).as(DocumentType.PDF)
                    .prioritizeWith(1000)
                    .schedule();

            boolean result = conversion.get(30, TimeUnit.SECONDS);

            if (!result) {
                System.err.println("Conversion failed.");
                return null;
            }

            return Files.readAllBytes(pdfFile.toPath());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
