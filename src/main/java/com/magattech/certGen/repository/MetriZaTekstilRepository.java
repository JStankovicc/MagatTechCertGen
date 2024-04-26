package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetriZaTekstilRepository extends JpaRepository<MetriZaTekstil, Integer> {
    Optional<MetriZaTekstil> findByBrojZapisnika(String brojZapisnika);

    List<MetriZaTekstil> findAllByOdobreno(boolean odobreno);
}
