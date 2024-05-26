package com.magattech.certGen.model.request;


import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasinaZaMerenjeRequest {
    private int id;

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

    private String proveraIspravnogVodjenja;
    private String proveraIspravnostiPokaznogUredjaja;

    private String merenje1;

    private String merenje2;

    private String merenje3;

    private String duzinaUzorka;

    private String debljinaUzorka;

    private String pokazivanjeMasine;

    private String odstupanjeOdPraveVrednostiDuzine;

    private String relativnaGreskaIzmereneDuzine;

    private String ndg1;

    private String mernaLupa;
    private String merniLenjir;
    private String pomicnoMerilo;

    private String skinutiZigovi;
    private String postavljeniZigovi;

    private String pravilnik;
    private String meriloIspunjavaZahteve;

    private String komentar2;

    private Date datum;

    private String zapisnikUneo;
    private String zapisnikOdobrio;

    private String odobreno;

    private String unit1;
    private String unit2;

    private String propisaniZahtevi;


}
