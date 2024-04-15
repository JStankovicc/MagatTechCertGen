package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.request.MernaTrakaSaViskomRequest;
import com.magattech.certGen.service.MernaTrakaSaViskomService;
import com.magattech.certGen.service.PDFGeneratorService;
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
    private final PDFGeneratorService pdfGeneratorService;
    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MernaTrakaSaViskomRequest mernaTrakaSaViskomRequest){
        mernaTrakaSaViskomService.add(mernaTrakaSaViskomRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaTrakaSaViskom>> getAll(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAll());
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMernaTrakaSaViskom() {
        byte[] pdfData = pdfGeneratorService.generateMernaTrakaSaViskom(new MernaTrakaSaViskom());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

}
