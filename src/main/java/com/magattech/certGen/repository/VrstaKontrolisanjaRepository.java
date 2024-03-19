package com.magattech.certGen.repository;

import com.magattech.certGen.model.VrstaKontrolisanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VrstaKontrolisanjaRepository extends JpaRepository<VrstaKontrolisanja, Integer> {
    Optional<VrstaKontrolisanja> findByDescription(String description);
}
