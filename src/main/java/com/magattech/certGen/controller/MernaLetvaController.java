package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.service.MernaLetvaService;
import com.magattech.certGen.service.DOCXGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mernaLetva")
@RequiredArgsConstructor
public class MernaLetvaController {

    private final MernaLetvaService mernaLetvaService;
    private final DOCXGeneratorService DOCXGeneratorService;

    @PostMapping("/add")
    public void addMernaLetva(@RequestBody MernaLetvaRequest mernaLetvaRequest){
        mernaLetvaService.add(mernaLetvaRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaLetva>> getAll(){
        return ResponseEntity.ok(mernaLetvaService.getAll());
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<MernaLetva>> getNeoverena(){
        return ResponseEntity.ok(mernaLetvaService.findAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        mernaLetvaService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMernaLetva(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaLetva mernaLetva = mernaLetvaService.getByBrojZapisnika(brojZapisnika);

        byte[] pdfData = DOCXGeneratorService.generateMernaLetva(mernaLetva);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printMernaLetvaResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaLetva mernaLetva = mernaLetvaService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = mernaLetva.getMeriloHeplper();

        byte[] pdfData = null;

        if(mernaLetva.isMeriloIspunjavaZahteve()){
            pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);
        }else{
            pdfData = DOCXGeneratorService.generateResenjeOOdbijanju(meriloHelper);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
