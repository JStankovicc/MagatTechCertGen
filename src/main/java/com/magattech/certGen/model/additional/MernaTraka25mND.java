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
@Table(name = "_merna_traka_25m_nd")
public class MernaTraka25mND {

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
    private String ndg13;
    private String ndg14;
    private String ndg15;
    private String ndg16;
    private String ndg17;
    private String ndg18;
    private String ndg19;
    private String ndg20;
    private String ndg21;
    private String ndg22;
    private String ndg23;
    private String ndg24;
    private String ndg25;
    private String ndg26;
    private String ndg27;
    private String ndr1;
}

