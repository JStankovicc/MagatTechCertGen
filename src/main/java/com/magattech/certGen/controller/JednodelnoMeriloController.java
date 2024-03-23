package com.magattech.certGen.controller;


import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import com.magattech.certGen.service.JednodelnoMeriloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
