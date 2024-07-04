package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MetriZaTekstil;
import com.magattech.certGen.model.merila.SlozivoMerilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MetriZaTekstilRepository extends JpaRepository<MetriZaTekstil, Integer> {
    Optional<MetriZaTekstil> findByBrojZapisnika(String brojZapisnika);

    List<MetriZaTekstil> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _metri_za_tekstil j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY CAST(SUBSTRING(j.broj_zapisnika, INSTR(j.broj_zapisnika, 'K') + 1) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);

    List<MetriZaTekstil> findAllByBrojZapisnikaLike(String formattedBrojSeta);
}
