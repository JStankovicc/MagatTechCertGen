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
        // Pretvaranje objekta u HTML string - ovdje zamijenite s vlastitom logikom za generiranje HTML-a
        String htmlContent = generateHtmlFromJednodelnoMerilo(jednodelnoMerilo);

        // Generiranje PDF-a iz HTML-a
        try {
            InputStream inputStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(inputStream, outputStream);

            // Spremi PDF datoteku na disk
            byte[] pdfBytes = outputStream.toByteArray();
            File pdfFile = File.createTempFile("generated_pdf", ".pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
            fileOutputStream.write(pdfBytes);
            fileOutputStream.close();

            // Otvaranje PDF datoteke pomoću Desktop klase
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Radna površina nije podržana.");
            }

            return pdfBytes;
        } catch (IOException e) {
            // Uhvatiti i obraditi iznimku ako se dogodi greška prilikom generiranja PDF-a
            e.printStackTrace();
            return null;
        }
    }

    // Metoda za generiranje HTML-a iz objekta JednodelnoMerilo
    private String generateHtmlFromJednodelnoMerilo(JednodelnoMerilo jednodelnoMerilo) {
        // Implementirajte logiku pretvorbe objekta u HTML string ovdje
        // Na primjer, možete koristiti biblioteku poput Thymeleaf ili FreeMarker za generiranje HTML-a iz predloška
        // ili jednostavno ručno generirati HTML string
        return "<html><body><h1>Hello, World!</h1><p>This is a PDF generated from object data.</p></body></html>";
    }
}