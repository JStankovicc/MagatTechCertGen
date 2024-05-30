package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
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

    @PostMapping("/update/{id}")
    public void updateSlozivoMerilo(@PathVariable String id, @RequestBody SlozivoMeriloRequest slozivoMeriloRequest){
        String newId = id.replace("_","/");
        slozivoMeriloService.update(newId,slozivoMeriloRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<SlozivoMerilo> getSlozivoMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        SlozivoMerilo slozivoMerilo = slozivoMeriloService.getByBrojZapisnika(brojZapisnika);
        return ResponseEntity.ok(slozivoMerilo);
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
        SlozivoMerilo slozivoMerilo = slozivoMeriloService.getByBrojZapisnika(brojZapisnika);
        String fileType;

        if (slozivoMerilo.isMeriloIspunjavaZahteve()) {
            fileType = "Uverenje o overavanju merila";
        } else {
            fileType = "Resenje o odbijanju merila";
        }

        return ResponseEntity.ok(fileType);
    }

    @GetMapping("/printSertifikat")
    public ResponseEntity<byte[]> printSertifikat(@RequestParam("brojZapisnika") String brojZapisnika){
        SlozivoMerilo slozivoMerilo = slozivoMeriloService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = slozivoMerilo.getMeriloHeplper();
        if(slozivoMerilo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
