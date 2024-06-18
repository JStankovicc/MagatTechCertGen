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
@Table(name = "_merna_traka_sa_viskom_nd")
public class MernaTrakaSaViskomND {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ndg1;
    private String ndg2;
    private String ndg3;
    private String ndg4;
    private String ndg5;
    private String ndg6;
    private String ndg7;
    private String ndg8;
    private String ndg9;
    private String ndg10;
    private String ndg11;
    private String ndg12;
    private String ndr1;
}
