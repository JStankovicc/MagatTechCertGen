package com.magattech.certGen.repository;

import com.magattech.certGen.model.BrojZapisnika;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BrojZapisnikaRepository extends JpaRepository<BrojZapisnika, Integer> {
    @Query("SELECT b FROM BrojZapisnika b ORDER BY b.godina DESC, b.broj DESC")
    BrojZapisnika getAktuelniBrojZapisnika();
}
