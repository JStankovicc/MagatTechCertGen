package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MasinaZaMerenjeRepository extends JpaRepository<MasinaZaMerenje, Integer> {
    Optional<MasinaZaMerenje> findByBrojZapisnika(String brojZapisnika);

    List<MasinaZaMerenje> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _masina_za_merenje j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY j.broj_zapisnika DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);

    List<MasinaZaMerenje> findAllByBrojZapisnikaLike(String formattedBrojSeta);
}
