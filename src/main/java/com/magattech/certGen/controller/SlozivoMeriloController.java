package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.SlozivoMeriloRequest;
import com.magattech.certGen.service.PDFGeneratorService;
import com.magattech.certGen.service.SlozivoMeriloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/slozivoMerilo")
@RequiredArgsConstructor
public class SlozivoMeriloController {

    private final SlozivoMeriloService slozivoMeriloService;
    private final PDFGeneratorService pdfGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody SlozivoMeriloRequest slozivoMeriloRequest){
        slozivoMeriloService.add(slozivoMeriloRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SlozivoMerilo>> getAll(){
        return ResponseEntity.ok(slozivoMeriloService.getAll());
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printSlozivoMerilo(@RequestParam("brojZapisnika") String brojZapisnika) {
        SlozivoMerilo slozivoMerilo = slozivoMeriloService.getByBrojZapisnika(brojZapisnika);
        byte[] pdfData = pdfGeneratorService.generateSlozivoMerilo(slozivoMerilo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
