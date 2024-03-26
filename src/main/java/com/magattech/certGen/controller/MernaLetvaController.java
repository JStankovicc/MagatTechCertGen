package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.MernaLetva;
import com.magattech.certGen.model.request.MernaLetvaRequest;
import com.magattech.certGen.service.MernaLetvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mernaLetva")
@RequiredArgsConstructor
public class MernaLetvaController {

    private final MernaLetvaService mernaLetvaService;

    @PostMapping("/add")
    public void addMernaLetva(@RequestBody MernaLetvaRequest mernaLetvaRequest){
        mernaLetvaService.add(mernaLetvaRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaLetva>> getAll(){
        return ResponseEntity.ok(mernaLetvaService.getAll());
    }
}
