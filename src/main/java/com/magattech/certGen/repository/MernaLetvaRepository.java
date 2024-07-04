package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MernaLetva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MernaLetvaRepository extends JpaRepository<MernaLetva, Integer> {
    Optional<MernaLetva> findByBrojZapisnika(String brojZapisnika);

    List<MernaLetva> findAllByOdobreno(boolean odobreno);

    @Query(value = "SELECT j.broj_zapisnika FROM _merna_letva j WHERE j.broj_zapisnika LIKE %:broj% ORDER BY CAST(SUBSTRING(j.broj_zapisnika, INSTR(j.broj_zapisnika, 'K') + 1) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findBiggestBrojZapisnika(@Param("broj") String broj);

    List<MernaLetva> findAllByBrojZapisnikaLike(String formattedBrojSeta);
}
