package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JednodelnoMeriloRepository extends JpaRepository<JednodelnoMerilo, Integer> {
    Optional<JednodelnoMerilo> findByBrojZapisnika(String brojZapisnika);

    List<JednodelnoMerilo> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _jednodelno_merilo j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY CAST(SUBSTRING(j.broj_zapisnika, INSTR(j.broj_zapisnika, 'K') + 1) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);


    List<JednodelnoMerilo> findAllByBrojZapisnikaLike(String brojSeta);
}
