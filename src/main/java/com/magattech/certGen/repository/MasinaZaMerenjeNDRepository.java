package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.MasinaZaMerenjeND;
import com.magattech.certGen.model.merila.MasinaZaMerenje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasinaZaMerenjeNDRepository extends JpaRepository<MasinaZaMerenjeND, Integer> {
}
