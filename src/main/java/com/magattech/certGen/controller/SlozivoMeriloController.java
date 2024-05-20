package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.SlozivoMeriloRequest;
import com.magattech.certGen.service.DOCXGeneratorService;
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
    private final DOCXGeneratorService DOCXGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody SlozivoMeriloRequest slozivoMeriloRequest){
        slozivoMeriloService.add(slozivoMeriloRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SlozivoMerilo>> getAll(){
        return ResponseEntity.ok(slozivoMeriloService.getAll());
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<SlozivoMerilo>> getNeodobrena(){
        return ResponseEntity.ok(slozivoMeriloService.getAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        slozivoMeriloService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printSlozivoMerilo(@RequestParam("brojZapisnika") String brojZapisnika) {
        SlozivoMerilo slozivoMerilo = slozivoMeriloService.getByBrojZapisnika(brojZapisnika);
        byte[] pdfData = DOCXGeneratorService.generateSlozivoMerilo(slozivoMerilo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printSlozivoMeriloResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        SlozivoMerilo slozivoMerilo = slozivoMeriloService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = slozivoMerilo.getMeriloHeplper();

        byte[] pdfData = null;

        if(slozivoMerilo.isMeriloIspunjavaZahteve()){
            pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);
        }else{
            pdfData = DOCXGeneratorService.generateResenjeOOdbijanju(meriloHelper);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
