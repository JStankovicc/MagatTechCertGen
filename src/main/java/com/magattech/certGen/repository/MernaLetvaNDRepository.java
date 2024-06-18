package com.magattech.certGen.repository;

import com.magattech.certGen.model.additional.MernaLetvaND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MernaLetvaNDRepository extends JpaRepository<MernaLetvaND, Integer> {
}
