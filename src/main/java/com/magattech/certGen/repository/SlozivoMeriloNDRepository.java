package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.SlozivoMeriloND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlozivoMeriloNDRepository extends JpaRepository<SlozivoMeriloND,Integer> {
}
