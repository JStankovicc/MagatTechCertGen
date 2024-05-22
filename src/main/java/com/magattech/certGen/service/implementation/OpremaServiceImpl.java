package com.magattech.certGen.service.implementation;

import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Oprema;
import com.magattech.certGen.model.request.OpremaRequest;
import com.magattech.certGen.repository.OpremaRepository;
import com.magattech.certGen.service.OpremaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpremaServiceImpl implements OpremaService {
    private final OpremaRepository opremaRepository;

    @Override
    public List<Oprema> findAll() {
        return opremaRepository.findAll();
    }

    @Override
    public Oprema getById(int id) {
        return opremaRepository.findById(id).orElse(Oprema.builder().serBrEtalona(null).build());
    }

    /**@Override
    public Oprema getByName(String name) {
        return opremaRepository.findByName(name).orElse(Oprema.builder().serBrEtalona(null).build());
    }*/

    @Override
    public Oprema getByEtalon(String serBrEtalona) {
        return opremaRepository.findBySerBrEtalona(serBrEtalona).orElse(Oprema.builder().serBrEtalona(null).build());
    }

    @Override
    public void addOprema(OpremaRequest opremaRequest) {
        Oprema oprema = Oprema.builder().tip(opremaRequest.getTip()).serBrEtalona(opremaRequest.getSerBrEtalona()).date(new Date()).build();
        opremaRepository.save(oprema);
    }

    @Override
    public void deleteOpremaById(String id) {
        opremaRepository.deleteById(Integer.valueOf(id));
    }

    @Override
    public List<Oprema> findByTip(OpremaType tip) {
        return opremaRepository.findByTip(tip);
    }

    @Override
    public Oprema findLatestByTip(OpremaType tip) {
        return opremaRepository.findTopByTipOrderByDateDesc(tip);
    }
}
