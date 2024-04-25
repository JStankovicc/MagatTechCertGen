package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.SlozivoMerilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlozivoMeriloRepository extends JpaRepository<SlozivoMerilo, Integer> {
    Optional<SlozivoMerilo> findByBrojZapisnika(String brojZapisnika);
}
