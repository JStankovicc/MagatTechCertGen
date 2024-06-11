package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MernaTrakaSaViskom25m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MernaTrakaSaViskom25mRepository extends JpaRepository<MernaTrakaSaViskom25m, Integer> {
    Optional<MernaTrakaSaViskom25m> findByBrojZapisnika(String brojZapisnika);

    List<MernaTrakaSaViskom25m> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _merna_traka_sa_viskom_25m j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY j.broj_zapisnika DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);
}
