package com.magattech.certGen.model.merila;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
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
@Table(name = "_jednodelno_merilo")
public class JednodelnoMerilo {
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

    @Column(length = 10)
    private String napomena;

    @Column(length = 10)
    private String odstupanje1;
    @Column(length = 10)
    private String odstupanje2;
    @Column(length = 10)
    private String odstupanje3;
    @Column(length = 10)
    private String odstupanje4;
    @Column(length = 10)
    private String odstupanje5;

    @Column(length = 10)
    private String ndg1;

    @Column(length = 10)
    private String greska1;
    @Column(length = 10)
    private String greska2;
    @Column(length = 10)
    private String greska3;
    @Column(length = 10)
    private String greska4;
    @Column(length = 10)
    private String greska5;
    @Column(length = 10)
    private String greska6;
    @Column(length = 10)
    private String greska7;
    @Column(length = 10)
    private String greska8;

    @Column(length = 10)
    private String greskaPodeljka1;
    @Column(length = 10)
    private String greskaPodeljka2;
    @Column(length = 10)
    private String greskaPodeljka3;
    @Column(length = 10)
    private String greskaPodeljka4;
    @Column(length = 10)
    private String greskaPodeljka5;
    @Column(length = 10)
    private String greskaPodeljka6;
    @Column(length = 10)
    private String greskaPodeljka7;
    @Column(length = 10)
    private String greskaPodeljka8;

    @Column(length = 10)
    private String ndg2;
    @Column(length = 10)
    private String ndr1;

    @Column(length = 10)
    private String odstupanje6;
    @Column(length = 10)
    private String odstupanje7;
    @Column(length = 10)
    private String odstupanje8;
    @Column(length = 10)
    private String odstupanje9;
    @Column(length = 10)
    private String odstupanje10;

    @Column(length = 10)
    private String ndg3;

    @Column(length = 10)
    private String greska9;
    @Column(length = 10)
    private String greska10;
    @Column(length = 10)
    private String greska11;
    @Column(length = 10)
    private String greska12;
    @Column(length = 10)
    private String greska13;
    @Column(length = 10)
    private String greska14;
    @Column(length = 10)
    private String greska15;
    @Column(length = 10)
    private String greska16;

    @Column(length = 10)
    private String greskaPodeljka9;
    @Column(length = 10)
    private String greskaPodeljka10;
    @Column(length = 10)
    private String greskaPodeljka11;
    @Column(length = 10)
    private String greskaPodeljka12;
    @Column(length = 10)
    private String greskaPodeljka13;
    @Column(length = 10)
    private String greskaPodeljka14;
    @Column(length = 10)
    private String greskaPodeljka15;
    @Column(length = 10)
    private String greskaPodeljka16;

    @Column(length = 10)
    private String ndg4;
    @Column(length = 10)
    private String ndr2;

    private String brojMernogLenjira;
    private String brojMerneLupe;

    private String skinutiZigovi;
    private String postavljeniZigovi;

    private boolean meriloIspunjavaZahteve;

    private String komentar2;
    
    private Date datum;

    @Column(length = 20)
    private String etalonirao;
    @Column(length = 20)
    private String odobrio;

    private boolean odobreno;

}
