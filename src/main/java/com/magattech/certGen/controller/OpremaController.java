package com.magattech.certGen.controller;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Oprema;
import com.magattech.certGen.model.request.OpremaRequest;
import com.magattech.certGen.model.request.UserRequest;
import com.magattech.certGen.service.OpremaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/oprema")
@RequiredArgsConstructor
public class OpremaController {
    private final OpremaService opremaService;

    @GetMapping("/all")
    public ResponseEntity<List<Oprema>> getAllOprema(){
        List<Oprema> oprema = opremaService.findAll();
        return ResponseEntity.ok(oprema);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody OpremaRequest opremaRequest){
        opremaService.addOprema(opremaRequest);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("id") String id){
        opremaService.deleteOpremaById(id);
    }

    @GetMapping("/types")
    public ResponseEntity<List<OpremaType>> getAllOpremaTypes() {
        List<OpremaType> types = Arrays.asList(OpremaType.values());
        return ResponseEntity.ok(types);
    }
}
