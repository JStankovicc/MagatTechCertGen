package com.magattech.certGen.controller;

import com.magattech.certGen.model.included.Kompanija;
import com.magattech.certGen.model.included.Proizvodjac;
import com.magattech.certGen.service.ProizvodjacService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proizvodjac")
@RequiredArgsConstructor
public class ProizvodjacController {

    private final ProizvodjacService proizvodjacService;

    @GetMapping("/all")
    public ResponseEntity<List<Proizvodjac>> getAll(){
        return ResponseEntity.ok(proizvodjacService.getAll());
    }
}
