package com.magattech.certGen;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.service.PDFGeneratorService;
import com.magattech.certGen.service.implementation.PDFGeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CertGenApplication {
	private final PDFGeneratorService pdfGeneratorService;

	public static void main(String[] args) {
		SpringApplication.run(CertGenApplication.class, args);
		PDFGeneratorService pdfGeneratorService1 = new PDFGeneratorServiceImpl();
		pdfGeneratorService1.generateJednodelnoMerilo(new JednodelnoMerilo());
	}

}
