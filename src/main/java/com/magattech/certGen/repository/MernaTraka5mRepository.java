package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MernaTraka25m;
import com.magattech.certGen.model.merila.MernaTraka5m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MernaTraka5mRepository extends JpaRepository<MernaTraka5m, Integer> {
    Optional<MernaTraka5m> findByBrojZapisnika(String brojZapisnika);

    List<MernaTraka5m> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _merna_traka_5m j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY j.broj_zapisnika DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);
}
