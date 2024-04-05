package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.service.PDFGeneratorService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.awt.Desktop;
import java.io.*;
import java.nio.charset.StandardCharsets;
import com.itextpdf.html2pdf.HtmlConverter;
@Service
@RequiredArgsConstructor
public class PDFGeneratorServiceImpl implements PDFGeneratorService {

    @Override
    public byte[] generateJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {
        String htmlContent = generateHtmlFromJednodelnoMerilo(jednodelnoMerilo);

        try {
            InputStream inputStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(inputStream, outputStream);

            // Spremi PDF datoteku na desktop
            byte[] pdfBytes = outputStream.toByteArray();
            File desktopDir = new File(System.getProperty("user.home") + File.separator + "Desktop");
            File pdfFile = File.createTempFile("generated_pdf", ".pdf", desktopDir);
            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
            fileOutputStream.write(pdfBytes);
            fileOutputStream.close();

            return pdfBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateHtmlFromJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {

        return "<html><body><h1>Hello, World!</h1><p>This is a PDF generated from object data.</p></body></html>";
    }
}