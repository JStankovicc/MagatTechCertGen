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

    private String brojZapisnika;

    private int vrstaKontrolisanja;
    private int podnosilacZahteva;
    private int korisnik;

    private String serijskiBroj;
    private String identifikacioniBroj;

    private int proizvodjac;

    private String oznakaTipa;
    private String sluzbenaOznakaTipa;

    private String merniOpseg;
    private String najmanjiPodeljak;
    private String klasaTacnosti;

    private String temperatura;
    private String vlaznostVazduha;

    private boolean meriloJeIspravno;

    private String napomena;

    private String odstupanje1;
    private String odstupanje2;
    private String odstupanje3;
    private String odstupanje4;
    private String odstupanje5;

    private String ndg1;

    private String greskaPodeljka1;
    private String greskaPodeljka2;
    private String greskaPodeljka3;
    private String greskaPodeljka4;
    private String greskaPodeljka5;
    private String greskaPodeljka6;
    private String greskaPodeljka7;
    private String greskaPodeljka8;

    private String ndg2;
    private String ndr1;

    private String odstupanje6;
    private String odstupanje7;
    private String odstupanje8;
    private String odstupanje9;
    private String odstupanje10;

    private String ndg3;

    private String greskaPodeljka9;
    private String greskaPodeljka10;
    private String greskaPodeljka11;
    private String greskaPodeljka12;
    private String greskaPodeljka13;
    private String greskaPodeljka14;
    private String greskaPodeljka15;
    private String greskaPodeljka16;

    private String ndg4;
    private String ndr2;

    private String brojMernogLenjira;
    private String brojMerneLupe;

    private String skinutiZigovi;
    private String postavljeniZigovi;

    private boolean meriloIspunjavaZahteve;

    private String komentar2;
    
    private Date datum;

    private String etalonirao;
    private String odobrio;

    private boolean odobreno;

}
