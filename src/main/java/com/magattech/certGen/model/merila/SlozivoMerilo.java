package com.magattech.certGen.model.merila;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_slozivo_merilo")
public class SlozivoMerilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10)
    private String brojZapisnika;

    private int vrstaKontrolisanja;
    private int podnosilacZahteva;
    private int korisnik;

    @Column(length = 10)
    private String serijskiBroj;
    @Column(length = 10)
    private String identifikacioniBroj;

    private int proizvodjac;
    @Column(length = 10)
    private String oznakaTipa;
    @Column(length = 10)
    private String sluzbenaOznakaTipa;

    @Column(length = 10)
    private String merniOpseg;
    @Column(length = 10)
    private String najmanjiPodeljak;
    @Column(length = 10)
    private String klasaTacnosti;

    @Column(length = 10)
    private String temperatura;
    @Column(length = 10)
    private String vlaznostVazduha;

    private boolean meriloJeIspravno;

    private Date datum;
}
