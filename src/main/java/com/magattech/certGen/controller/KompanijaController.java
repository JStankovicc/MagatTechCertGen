package com.magattech.certGen.controller;

import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.service.KompanijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kompanija")
@RequiredArgsConstructor
public class KompanijaController {
    private final KompanijaService kompanijaService;

    @GetMapping("/all")
    public ResponseEntity<List<Kompanija>> getAll(){
        return ResponseEntity.ok(kompanijaService.getAll());
    }
}
