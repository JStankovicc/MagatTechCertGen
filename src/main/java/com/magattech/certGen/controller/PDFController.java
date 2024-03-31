package com.magattech.certGen.controller;

import com.magattech.certGen.service.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pdf")
public class PDFController {
    private final PDFGeneratorService pdfGeneratorService;
    @GetMapping("/jednodelnoMerilo")
    public ResponseEntity<byte[]> generateCalibrationCertificate(){
        return ResponseEntity.ok(null);

    }
}
