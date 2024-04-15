package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
import com.magattech.certGen.service.JednodelnoMeriloService;
import com.magattech.certGen.service.MasinaZaMerenjeService;
import com.magattech.certGen.service.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/masinaZaMerenje")
@RequiredArgsConstructor
public class MasinaZaMerenjeController {

    private final MasinaZaMerenjeService masinaZaMerenjeService;
    private final PDFGeneratorService pdfGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MasinaZaMerenjeRequest masinaZaMerenjeRequest){
        System.out.println("DOBIJEN ZAHTEV");
        masinaZaMerenjeService.add(masinaZaMerenjeRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MasinaZaMerenje>> getAll(){
        return ResponseEntity.ok(masinaZaMerenjeService.getAll());
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMasinaZaMerenje() {
        byte[] pdfData = pdfGeneratorService.generateMasinaZaMerenje(new MasinaZaMerenje());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
