package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.request.MernaTrakaSaViskomRequest;
import com.magattech.certGen.service.MernaTrakaSaViskomService;
import com.magattech.certGen.service.DOCXGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mernaTrakaSaViskom")
@RequiredArgsConstructor
public class MernaTrakaSaViskomController {
    private final MernaTrakaSaViskomService mernaTrakaSaViskomService;
    private final DOCXGeneratorService DOCXGeneratorService;
    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MernaTrakaSaViskomRequest mernaTrakaSaViskomRequest){
        mernaTrakaSaViskomService.add(mernaTrakaSaViskomRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaTrakaSaViskom>> getAll(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAll());
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<MernaTrakaSaViskom>> getAllNeoverena(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        mernaTrakaSaViskomService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMernaTrakaSaViskom(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaTrakaSaViskom mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);

        byte[] pdfData = DOCXGeneratorService.generateMernaTrakaSaViskom(mernaTrakaSaViskom);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printMernaTrakaSaViskomResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaTrakaSaViskom mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = mernaTrakaSaViskom.getMeriloHeplper();

        byte[] pdfData = null;

        if(mernaTrakaSaViskom.isMeriloIspunjavaZahteve()){
            pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);
        }else{
            pdfData = DOCXGeneratorService.generateResenjeOOdbijanju(meriloHelper);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
