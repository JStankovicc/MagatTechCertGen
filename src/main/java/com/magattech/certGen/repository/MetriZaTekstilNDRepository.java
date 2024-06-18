package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.MetriZaTekstilND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetriZaTekstilNDRepository extends JpaRepository<MetriZaTekstilND, Integer> {
}
