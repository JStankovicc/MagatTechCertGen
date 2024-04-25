package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MasinaZaMerenje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasinaZaMerenjeRepository extends JpaRepository<MasinaZaMerenje, Integer> {
    Optional<MasinaZaMerenje> findByBrojZapisnika(String brojZapisnika);
}
