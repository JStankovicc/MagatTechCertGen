package com.magattech.certGen.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JednodelnoMeriloRequest {

    private String token;

    private String brojZapisnika;

    private String vrstaKontrolisanja;
    private String podnosilacZahteva;
    private String korisnik;

    private String serijskiBroj;
    private String identifikacioniBroj;

    private String proizvodjac;

    private String oznakaTipa;
    private String sluzbenaOznakaTipa;

    private String merniOpseg;
    private String najmanjiPodeljak;
    private String klasaTacnosti;

    private String temperatura;
    private String vlaznostVazduha;

    private String meriloJeIspravno;

    private String napomena;

    private String odstupanje1;
    private String odstupanje2;
    private String odstupanje3;
    private String odstupanje4;
    private String odstupanje5;

    private String ndg1;

    private String greska1;
    private String greska2;
    private String greska3;
    private String greska4;
    private String greska5;
    private String greska6;
    private String greska7;
    private String greska8;

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

    private String greska9;
    private String greska10;
    private String greska11;
    private String greska12;
    private String greska13;
    private String greska14;
    private String greska15;
    private String greska16;

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

    private String meriloIspunjavaZahteve;

    private String komentar2;

    private Date datum;

}
