package com.magattech.certGen.repository;

import com.magattech.certGen.model.included.Oprema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpremaRepository extends JpaRepository<Oprema, Integer> {
    Optional<Oprema> findByName(String name);

    Optional<Oprema> findBySerBrEtalona(String serBrEtalona);
}
