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
	public static void main(String[] args) {
		SpringApplication.run(CertGenApplication.class, args);
	}

}
