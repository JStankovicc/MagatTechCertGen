package com.magattech.certGen.repository;

import com.magattech.certGen.model.enums.OpremaType;
import com.magattech.certGen.model.included.Oprema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpremaRepository extends JpaRepository<Oprema, Integer> {
    //Optional<Oprema> findByName(String name);
    List<Oprema> findByTip(OpremaType tip);
    Optional<Oprema> findBySerBrEtalona(String serBrEtalona);
    Oprema findTopByTipOrderByDateDesc(OpremaType tip);
}
