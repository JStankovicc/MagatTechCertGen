package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.MernaTraka25mND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MernaTraka25mNDRepository extends JpaRepository<MernaTraka25mND, Integer> {
}
