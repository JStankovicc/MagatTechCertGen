package com.magattech.certGen.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MernaTrakaSaViskomRequest {

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
    private String odstupanje6;
    private String odstupanje7;
    private String odstupanje8;
    private String odstupanje9;
    private String odstupanje10;
    private String odstupanje11;

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

    private String greska1a;
    private String greska2a;
    private String greska3a;
    private String greska4a;
    private String greska5a;
    private String greska6a;
    private String greska7a;
    private String greska8a;

    private String greska1b;
    private String greska2b;
    private String greska3b;
    private String greska4b;
    private String greska5b;
    private String greska6b;
    private String greska7b;
    private String greska8b;

    private String greskaPodeljka1;
    private String greskaPodeljka2;
    private String greskaPodeljka3;
    private String greskaPodeljka4;
    private String greskaPodeljka5;
    private String greskaPodeljka6;
    private String greskaPodeljka7;
    private String greskaPodeljka8;

    private String ndg12;
    private String ndr1;

    private String brojMernogLenjira;
    private String brojMerneLupe;

    private String skinutiZigovi;
    private String postavljeniZigovi;

    private String meriloIspunjavaZahteve;

    private String komentar2;

    private Date datum;

    private String unit1;
    private String unit2;

    private String zapisnikUneo;
    private String zapisnikOdobrio;

    private String propisaniZahtevi;

    private String razlogOdbijanja;
}
