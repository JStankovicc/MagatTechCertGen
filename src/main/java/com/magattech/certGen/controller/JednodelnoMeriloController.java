package com.magattech.certGen.controller;


import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.service.JednodelnoMeriloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jednodelnoMerilo")
@RequiredArgsConstructor
public class JednodelnoMeriloController {

    private final JednodelnoMeriloService jednodelnoMeriloService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody JednodelnoMeriloRequest jednodelnoMeriloRequest){
        System.out.println("TEST");
        System.out.println(jednodelnoMeriloRequest.getDatum());
        System.out.println(jednodelnoMeriloRequest.getToken());
        jednodelnoMeriloService.add(jednodelnoMeriloRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JednodelnoMerilo>> getAll(){
        return ResponseEntity.ok(jednodelnoMeriloService.getAll());
    }

}
