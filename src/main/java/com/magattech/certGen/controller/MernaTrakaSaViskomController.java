package com.magattech.certGen.controller;

import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import com.magattech.certGen.model.request.MernaTrakaSaViskomRequest;
import com.magattech.certGen.service.MernaTrakaSaViskomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mernaTrakaSaViskom")
@RequiredArgsConstructor
public class MernaTrakaSaViskomController {
    private final MernaTrakaSaViskomService mernaTrakaSaViskomService;

    @PostMapping("/add")
    public void addJednodelnoMerilo(@RequestBody MernaTrakaSaViskomRequest mernaTrakaSaViskomRequest){
        mernaTrakaSaViskomService.add(mernaTrakaSaViskomRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MernaTrakaSaViskom>> getAll(){
        return ResponseEntity.ok(mernaTrakaSaViskomService.getAll());
    }

}
