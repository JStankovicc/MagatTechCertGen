package com.magattech.certGen.controller;


import com.magattech.certGen.model.additional.JednodelnoMeriloND;
import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.service.JednodelnoMeriloNDService;
import com.magattech.certGen.service.JednodelnoMeriloService;
import com.magattech.certGen.service.DOCXGeneratorService;
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
    private final JednodelnoMeriloNDService jednodelnoMeriloNDService;
    private final DOCXGeneratorService DOCXGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody JednodelnoMeriloRequest jednodelnoMeriloRequest){
        jednodelnoMeriloService.add(jednodelnoMeriloRequest);
    }

    @PostMapping("/update/{id}")
    public void updateJednodelnoMerilo(@PathVariable String id, @RequestBody JednodelnoMeriloRequest jednodelnoMeriloRequest){
        String newId = id.replace("_","/");
        jednodelnoMeriloService.update(newId,jednodelnoMeriloRequest);
    }
    @GetMapping("/get")
    public ResponseEntity<JednodelnoMerilo> getJednodelnoMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        JednodelnoMerilo jednodelnoMerilo = jednodelnoMeriloService.getByBrojZapisnika(brojZapisnika);
        return ResponseEntity.ok(jednodelnoMerilo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JednodelnoMerilo>> getAll(){
        return ResponseEntity.ok(jednodelnoMeriloService.getAll());
    }

    @GetMapping("/allByBrojSeta")
    public ResponseEntity<List<JednodelnoMerilo>> getAllByBrojSeta(@RequestParam("brojSeta") String brojSeta){
        return ResponseEntity.ok(jednodelnoMeriloService.getAllByBrojSeta(brojSeta));
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<JednodelnoMerilo>> getNeoverena(){
        return ResponseEntity.ok(jednodelnoMeriloService.getNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        jednodelnoMeriloService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printJednodelnoMerilo(@RequestParam("brojZapisnika") String brojZapisnika) {
        JednodelnoMerilo jednodelnoMerilo = jednodelnoMeriloService.getByBrojZapisnika(brojZapisnika);

        if(jednodelnoMerilo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfData = DOCXGeneratorService.generateJednodelnoMerilo(jednodelnoMerilo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printJednodelnoMeriloResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        JednodelnoMerilo jednodelnoMerilo = jednodelnoMeriloService.getByBrojZapisnika(brojZapisnika);

        MeriloHelper meriloHelper = jednodelnoMerilo.getMeriloHeplper();

        byte[] pdfData = null;

        if(jednodelnoMerilo.isMeriloIspunjavaZahteve()){
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
        JednodelnoMerilo jednodelnoMerilo = jednodelnoMeriloService.getByBrojZapisnika(brojZapisnika);
        String fileType;

        if (jednodelnoMerilo.isMeriloIspunjavaZahteve()) {
            fileType = "Uverenje o overavanju merila";
        } else {
            fileType = "Resenje o odbijanju merila";
        }

        return ResponseEntity.ok(fileType);
    }

    @GetMapping("/printSertifikat")
    public ResponseEntity<byte[]> printSertifikat(@RequestParam("brojZapisnika") String brojZapisnika){
        JednodelnoMerilo jednodelnoMerilo = jednodelnoMeriloService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = jednodelnoMerilo.getMeriloHeplper();
        if(jednodelnoMerilo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/getND")
    public ResponseEntity<JednodelnoMeriloND> getND(){
        return ResponseEntity.ok(jednodelnoMeriloNDService.getJednodelnoMeriloND());
    }

}
