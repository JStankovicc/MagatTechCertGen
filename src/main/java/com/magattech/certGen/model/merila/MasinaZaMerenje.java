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
@Table(name = "_masina_za_merenje")
public class MasinaZaMerenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10)
    private String brojZapisnika;

    private String vrstaKontrolisanja;
    private String podnosilacZahteva;
    private String korisnik;

    @Column(length = 10)
    private String serijskiBroj;
    @Column(length = 10)
    private String identifikacioniBroj;

    private String proizvodjac;
    @Column(length = 10)
    private String oznakaTipa;
    @Column(length = 10)
    private String sluzbenaOznakaTipa;

    @Column(length = 30)
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

    @Column(length = 10)
    private String napomena;

    private String proveraIspravnogVodjenja;
    private boolean proveraIspravnostiPokaznogUredjaja;

    @Column(length = 10)
    private String merenje1;

    @Column(length = 10)
    private String merenje2;

    @Column(length = 10)
    private String merenje3;

    @Column(length = 10)
    private String duzinaUzorka;

    @Column(length = 10)
    private String debljinaUzorka;

    @Column(length = 10)
    private String pokazivanjeMasine;

    @Column(length = 10)
    private String odstupanjeOdPraveVrednostiDuzine;

    @Column(length = 10)
    private String relativnaGreskaIzmereneDuzine;

    @Column(length = 10)
    private String ndg1;

    private String brojMernogLenjira;
    private String brojPomicnogMerila;

    private String skinutiZigovi;
    private String postavljeniZigovi;

    private boolean meriloIspunjavaZahteve;

    private String komentar2;

    private Date datum;

    @Column(length = 30)
    private String etalonirao;
    @Column(length = 30)
    private String odobrio;

    private boolean odobreno;
}
