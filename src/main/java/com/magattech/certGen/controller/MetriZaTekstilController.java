package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.request.MetriZaTekstilRequest;
import com.magattech.certGen.service.MetriZaTekstilService;
import com.magattech.certGen.service.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metriZaTekstil")
@RequiredArgsConstructor
public class MetriZaTekstilController {

    private final MetriZaTekstilService metriZaTekstilService;
    private final PDFGeneratorService pdfGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MetriZaTekstilRequest metriZaTekstilRequest){
        metriZaTekstilService.add(metriZaTekstilRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MetriZaTekstil>> getAll(){
        return ResponseEntity.ok(metriZaTekstilService.getAll());
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMetriZaTekstil(@RequestParam("brojZapisnika") String brojZapisnika) {
        MetriZaTekstil metriZaTekstil = metriZaTekstilService.getByBrojZapisnika(brojZapisnika);
        byte[] pdfData = pdfGeneratorService.generateMetriZaTekstil(metriZaTekstil);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
