package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.JednodelnoMerilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JednodelnoMeriloRepository extends JpaRepository<JednodelnoMerilo, Integer> {
    Optional<JednodelnoMerilo> findByBrojZapisnika(String brojZapisnika);

    List<JednodelnoMerilo> findAllByOdobreno(boolean odobreno);
}
