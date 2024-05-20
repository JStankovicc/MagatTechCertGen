package com.magattech.certGen.controller;

import com.magattech.certGen.service.BrojZapisnikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
