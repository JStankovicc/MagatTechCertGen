package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.request.MernaLetvaRequest;
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

    @PostMapping("/update/{id}")
    public void updateMernaTraka(@PathVariable String id, @RequestBody MernaTrakaSaViskomRequest mernaTrakaSaViskomRequest){
        String newId = id.replace("_","/");
        mernaTrakaSaViskomService.update(newId,mernaTrakaSaViskomRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<MernaTrakaSaViskom> getMernaTraka(@RequestParam("brojZapisnika") String brojZapisnika){
        MernaTrakaSaViskom mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
        return ResponseEntity.ok(mernaTrakaSaViskom);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaTrakaSaViskom>> getAll(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAll());
    }

    @GetMapping("/allByBrojSeta")
    public ResponseEntity<List<MernaTrakaSaViskom>> getAllByBrojSeta(@RequestParam("brojSeta") String brojSeta){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAllByBrojSeta(brojSeta));
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
            pdfData = DOCXGeneratorService.generateUverenjeOOveravanju(meriloHelper);
        }else{
            pdfData = DOCXGeneratorService.generateResenjeOOdbijanju(meriloHelper);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/checkType")
    public ResponseEntity<String> checkType(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaTrakaSaViskom mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
        String fileType;

        if (mernaTrakaSaViskom.isMeriloIspunjavaZahteve()) {
            fileType = "Uverenje o overavanju merila";
        } else {
            fileType = "Resenje o odbijanju merila";
        }

        return ResponseEntity.ok(fileType);
    }

    @GetMapping("/printSertifikat")
    public ResponseEntity<byte[]> printSertifikat(@RequestParam("brojZapisnika") String brojZapisnika){
        MernaTrakaSaViskom mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = mernaTrakaSaViskom.getMeriloHeplper();
        if(mernaTrakaSaViskom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
