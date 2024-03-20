package com.magattech.certGen.controller;

import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.service.VrstaKontrolisanjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vrstakontrolisanja")
@RequiredArgsConstructor
public class VrstaKontrolisanjaController {

    private final VrstaKontrolisanjaService vrstaKontrolisanjaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<VrstaKontrolisanja>> getAll(){
        System.out.println("OVDE");
        return ResponseEntity.ok(vrstaKontrolisanjaService.getAll());
    }
}
