package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.JednodelnoMeriloND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JednodelnoMeriloNDRepository extends JpaRepository<JednodelnoMeriloND, Integer> {
}
