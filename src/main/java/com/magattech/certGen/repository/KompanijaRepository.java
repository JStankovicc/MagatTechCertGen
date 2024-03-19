package com.magattech.certGen.repository;

import com.magattech.certGen.model.Kompanija;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KompanijaRepository extends JpaRepository<Kompanija,Integer> {
    Optional<Kompanija> findByName(String name);
}
