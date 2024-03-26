package com.magattech.certGen.controller;

import com.magattech.certGen.service.PDFGeneratorServvice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pdf")
public class PDFController {
    private final PDFGeneratorServvice pdfGeneratorServvice;
    @GetMapping("/jednodelnoMerilo")
    public ResponseEntity<byte[]> generateCalibrationCertificate(){
        return ResponseEntity.ok(null);

    }
}
