package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MernaLetva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MernaLetvaRepository extends JpaRepository<MernaLetva, Integer> {
    Optional<MernaLetva> findByBrojZapisnika(String brojZapisnika);
}
