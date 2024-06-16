package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.SlozivoMerilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SlozivoMeriloRepository extends JpaRepository<SlozivoMerilo, Integer> {
    Optional<SlozivoMerilo> findByBrojZapisnika(String brojZapisnika);

    List<SlozivoMerilo> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _slozivo_merilo j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY j.broj_zapisnika DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);

    List<SlozivoMerilo> findAllByBrojZapisnikaLike(String formattedBrojSeta);
}
