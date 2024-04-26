package com.magattech.certGen;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@RequiredArgsConstructor
public class CertGenApplication {
	public static void main(String[] args) {
		SpringApplication.run(CertGenApplication.class, args);
	}

}
