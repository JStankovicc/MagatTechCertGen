package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
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
        masinaZaMerenjeService.add(masinaZaMerenjeRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MasinaZaMerenje>> getAll(){
        return ResponseEntity.ok(masinaZaMerenjeService.getAll());
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<MasinaZaMerenje>> getNeoverena(){
        return ResponseEntity.ok(masinaZaMerenjeService.findAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        masinaZaMerenjeService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMasinaZaMerenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MasinaZaMerenje masinaZaMerenje = masinaZaMerenjeService.getByBrojZapisnika(brojZapisnika);
        byte[] pdfData = pdfGeneratorService.generateMasinaZaMerenje(masinaZaMerenje);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
