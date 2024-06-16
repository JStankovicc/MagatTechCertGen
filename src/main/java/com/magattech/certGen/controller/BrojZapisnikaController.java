package com.magattech.certGen.controller;

import com.magattech.certGen.service.BrojZapisnikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brojZapisnika")
@RequiredArgsConstructor
public class BrojZapisnikaController {

    private final BrojZapisnikaService brojZapisnikaService;

    @GetMapping
    public String brojZapisnika() {
        return brojZapisnikaService.getAktuelniBrojZapisnika();
    }

    @GetMapping("/update")
    public void update(){
        brojZapisnikaService.updateZapisnik();
    }

    @GetMapping("/checkAndUpdate")
    public void checkAndUpdate(){
        brojZapisnikaService.checkAndUpdateZapisnik();
    }

    @GetMapping("/getThisYear")
    public List<String> getThisYear(){
        return brojZapisnikaService.getThisYear();
    }

    @GetMapping("/getWithoutThisYear")
    public List<String> getWithoutThisYear(){
        return brojZapisnikaService.getWithoutThisYear();
    }

    @GetMapping("/all")
    public List<String> getAll(){
        return brojZapisnikaService.getAll();
    }

}
