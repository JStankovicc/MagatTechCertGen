package com.magattech.certGen.controller;


import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.service.JednodelnoMeriloService;
import com.magattech.certGen.service.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jednodelnoMerilo")
@RequiredArgsConstructor
public class JednodelnoMeriloController {

    private final JednodelnoMeriloService jednodelnoMeriloService;
    private final PDFGeneratorService pdfGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody JednodelnoMeriloRequest jednodelnoMeriloRequest){
        jednodelnoMeriloService.add(jednodelnoMeriloRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JednodelnoMerilo>> getAll(){
        return ResponseEntity.ok(jednodelnoMeriloService.getAll());
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printJednodelnoMerilo(@RequestParam("brojZapisnika") String brojZapisnika) {
        JednodelnoMerilo jednodelnoMerilo = jednodelnoMeriloService.getByBrojZapisnika(brojZapisnika);

        if(jednodelnoMerilo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfData = pdfGeneratorService.generateJednodelnoMerilo(jednodelnoMerilo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }


}
