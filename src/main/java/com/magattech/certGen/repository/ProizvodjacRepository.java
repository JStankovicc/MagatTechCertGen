package com.magattech.certGen.repository;

import com.magattech.certGen.model.included.Proizvodjac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Integer> {
    Optional<Proizvodjac> findByName(String name);
}
