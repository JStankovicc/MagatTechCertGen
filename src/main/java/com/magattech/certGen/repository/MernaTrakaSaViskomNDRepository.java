package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.MernaTrakaSaViskomND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MernaTrakaSaViskomNDRepository extends JpaRepository<MernaTrakaSaViskomND, Integer> {
}
