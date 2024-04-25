package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import com.magattech.certGen.model.request.SlozivoMeriloRequest;
import com.magattech.certGen.repository.SlozivoMeriloRepository;
import com.magattech.certGen.service.SlozivoMeriloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlozivoMeriloServiceImpl implements SlozivoMeriloService {
    private final SlozivoMeriloRepository slozivoMeriloRepository;
    @Override
    public List<SlozivoMerilo> getAll() {
        return slozivoMeriloRepository.findAll();
    }

    @Override
    public void add(SlozivoMeriloRequest slozivoMeriloRequest) {

    }

    @Override
    public SlozivoMerilo getByBrojZapisnika(String brojZapisnika) {
        return slozivoMeriloRepository.findByBrojZapisnika(brojZapisnika).orElse(SlozivoMerilo.builder().brojZapisnika(null).build());
    }
}
