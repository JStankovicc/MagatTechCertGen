package com.magattech.certGen.controller;


import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom25m;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.model.request.MernaTrakaSaViskom25mRequest;
import com.magattech.certGen.service.DOCXGeneratorService;
import com.magattech.certGen.service.MernaTrakaSaViskom25mService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mernaTrakaSaViskom25m")
@RequiredArgsConstructor
public class MernaTrakaSaViskom25mController {
    private final MernaTrakaSaViskom25mService mernaTrakaSaViskomService;
    private final DOCXGeneratorService DOCXGeneratorService;
    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MernaTrakaSaViskom25mRequest mernaTrakaSaViskomRequest){
        mernaTrakaSaViskomService.add(mernaTrakaSaViskomRequest);
    }

    @PostMapping("/update/{id}")
    public void updateMernaTraka(@PathVariable String id, @RequestBody MernaTrakaSaViskom25mRequest mernaTrakaSaViskomRequest){
        String newId = id.replace("_","/");
        mernaTrakaSaViskomService.update(newId,mernaTrakaSaViskomRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<MernaTrakaSaViskom25m> getMernaTraka(@RequestParam("brojZapisnika") String brojZapisnika){
        MernaTrakaSaViskom25m mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
        return ResponseEntity.ok(mernaTrakaSaViskom);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaTrakaSaViskom25m>> getAll(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAll());
    }

    @GetMapping("/allByBrojSeta")
    public ResponseEntity<List<MernaTrakaSaViskom25m>> getAllByBrojSeta(@RequestParam("brojSeta") String brojSeta){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAllByBrojSeta(brojSeta));
    }

    @GetMapping("/neoverena")
    public ResponseEntity<List<MernaTrakaSaViskom25m>> getAllNeoverena(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAllNeoverena());
    }

    @GetMapping("/odobri")
    public void odobriMerilo(@RequestParam("brojZapisnika") String brojZapisnika){
        mernaTrakaSaViskomService.odobri(brojZapisnika);
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMernaTrakaSaViskom(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaTrakaSaViskom25m mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);

        byte[] pdfData = DOCXGeneratorService.generateMernaTrakaSaViskom25m(mernaTrakaSaViskom);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/printResenje")
    public ResponseEntity<byte[]> printMernaTrakaSaViskomResenje(@RequestParam("brojZapisnika") String brojZapisnika) {
        MernaTrakaSaViskom25m mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
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
        MernaTrakaSaViskom25m mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
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
        MernaTrakaSaViskom25m mernaTrakaSaViskom = mernaTrakaSaViskomService.getByBrojZapisnika(brojZapisnika);
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

