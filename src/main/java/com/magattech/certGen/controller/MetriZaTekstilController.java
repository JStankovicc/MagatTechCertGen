package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.request.MetriZaTekstilRequest;
import com.magattech.certGen.service.MetriZaTekstilService;
import com.magattech.certGen.service.DOCXGeneratorService;
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
    private final DOCXGeneratorService DOCXGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MetriZaTekstilRequest metriZaTekstilRequest){
        metriZaTekstilService.add(metriZaTekstilRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MetriZaTekstil>> getAll(){
        return ResponseEntity.ok(metriZaTekstilService.getAll());
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<MetriZaTekstil>> getNeoverena(){
        return ResponseEntity.ok(metriZaTekstilService.getAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        metriZaTekstilService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMetriZaTekstil(@RequestParam("brojZapisnika") String brojZapisnika) {
        MetriZaTekstil metriZaTekstil = metriZaTekstilService.getByBrojZapisnika(brojZapisnika);
        byte[] pdfData = DOCXGeneratorService.generateMetriZaTekstil(metriZaTekstil);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printMetriZaTekstilResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MetriZaTekstil metriZaTekstil = metriZaTekstilService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = metriZaTekstil.getMeriloHeplper();

        byte[] pdfData = null;

        if(metriZaTekstil.isMeriloIspunjavaZahteve()){
            pdfData = DOCXGeneratorService.generateUverenjeOOveravanju(meriloHelper);
        }else{
            pdfData = DOCXGeneratorService.generateResenjeOOdbijanju(meriloHelper);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printSertifikat")
    public ResponseEntity<byte[]> printSertifikat(@RequestParam("brojZapisnika") String brojZapisnika){
        MetriZaTekstil metriZaTekstil = metriZaTekstilService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = metriZaTekstil.getMeriloHeplper();
        if(metriZaTekstil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
