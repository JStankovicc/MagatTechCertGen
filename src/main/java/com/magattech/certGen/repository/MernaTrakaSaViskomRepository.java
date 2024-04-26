package com.magattech.certGen.repository;

import com.magattech.certGen.model.merila.MernaTrakaSaViskom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MernaTrakaSaViskomRepository extends JpaRepository<MernaTrakaSaViskom, Integer> {
    Optional<MernaTrakaSaViskom> findByBrojZapisnika(String brojZapisnika);

    List<MernaTrakaSaViskom> findAllByOdobreno(boolean odobreno);
}
