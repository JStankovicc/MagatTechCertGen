package com.magattech.certGen.model.additional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_jednodelno_merilo_nd")
public class JednodelnoMeriloND {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ndg1;
    private String ndg2;
    private String ndg3;
    private String ndg4;
    private String ndr1;
    private String ndr2;
}
