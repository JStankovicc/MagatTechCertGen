package com.magattech.certGen.controller;

import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import com.magattech.certGen.model.request.MasinaZaMerenjeRequest;
import com.magattech.certGen.service.MasinaZaMerenjeService;
import com.magattech.certGen.service.DOCXGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/masinaZaMerenje")
@RequiredArgsConstructor
public class MasinaZaMerenjeController {

    private final MasinaZaMerenjeService masinaZaMerenjeService;
    private final DOCXGeneratorService DOCXGeneratorService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MasinaZaMerenjeRequest masinaZaMerenjeRequest){
        masinaZaMerenjeService.add(masinaZaMerenjeRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MasinaZaMerenje>> getAll(){
        return ResponseEntity.ok(masinaZaMerenjeService.getAll());
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<MasinaZaMerenje>> getNeoverena(){
        return ResponseEntity.ok(masinaZaMerenjeService.findAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        masinaZaMerenjeService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMasinaZaMerenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MasinaZaMerenje masinaZaMerenje = masinaZaMerenjeService.getByBrojZapisnika(brojZapisnika);
        byte[] pdfData = DOCXGeneratorService.generateMasinaZaMerenje(masinaZaMerenje);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printMasinaZaMerenjeResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MasinaZaMerenje masinaZaMerenje = masinaZaMerenjeService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = masinaZaMerenje.getMeriloHeplper();

        byte[] pdfData = null;

        if(masinaZaMerenje.isMeriloIspunjavaZahteve()){
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
        MasinaZaMerenje masinaZaMerenje = masinaZaMerenjeService.getByBrojZapisnika(brojZapisnika);
        String fileType;

        if (masinaZaMerenje.isMeriloIspunjavaZahteve()) {
            fileType = "Uverenje o overavanju merila";
        } else {
            fileType = "Resenje o odbijanju merila";
        }

        return ResponseEntity.ok(fileType);
    }

    @GetMapping("/printSertifikat")
    public ResponseEntity<byte[]> printSertifikat(@RequestParam("brojZapisnika") String brojZapisnika){
        MasinaZaMerenje masinaZaMerenje = masinaZaMerenjeService.getByBrojZapisnika(brojZapisnika);
        MeriloHelper meriloHelper = masinaZaMerenje.getMeriloHeplper();

        byte[] pdfData = DOCXGeneratorService.generateSertifikatOKontrolisanju(meriloHelper);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
