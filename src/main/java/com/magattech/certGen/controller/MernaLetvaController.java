package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.service.MernaLetvaService;
import com.magattech.certGen.service.PDFGeneratorService;
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
    private final PDFGeneratorService pdfGeneratorService;

    @PostMapping("/add")
    public void addMernaLetva(@RequestBody MernaLetvaRequest mernaLetvaRequest){
        mernaLetvaService.add(mernaLetvaRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaLetva>> getAll(){
        return ResponseEntity.ok(mernaLetvaService.getAll());
    }

    @GetMapping("/print")
    public ResponseEntity<byte[]> printMernaLetva() {
        byte[] pdfData = pdfGeneratorService.generateMernaLetva(new MernaLetva());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
