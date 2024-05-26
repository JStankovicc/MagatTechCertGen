package com.magattech.certGen.model.merila;

import com.magattech.certGen.model.helper.MeriloHelper;
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

    //@Column(length = 10)
    private String brojZapisnika;

    private String vrstaKontrolisanja;
    private String podnosilacZahteva;
    private String korisnik;

    //@Column(length = 10)
    private String serijskiBroj;
    //@Column(length = 10)
    private String identifikacioniBroj;

    private String proizvodjac;
    //@Column(length = 20)
    private String oznakaTipa;
    //@Column(length = 20)
    private String sluzbenaOznakaTipa;

    //@Column(length = 30)
    private String merniOpseg;
    //@Column(length = 10)
    private String najmanjiPodeljak;
    @Column(length = 10)
    private String klasaTacnosti;

    //@Column(length = 10)
    private String temperatura;
    //@Column(length = 10)
    private String vlaznostVazduha;

    private boolean meriloJeIspravno;

    //@Column(length = 10)
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

    private String pravilnik;
    private boolean meriloIspunjavaZahteve;

    private String komentar2;

    private Date datum;

    //@Column(length = 30)
    private String etalonirao;
    //@Column(length = 30)
    private String odobrio;

    private boolean odobreno;

//    private String unit1;
//    private String unit2;

    private String propisaniZahtevi;

    public MeriloHelper getMeriloHeplper(){
        MeriloHelper meriloHelper = MeriloHelper.builder().nazivMerila("Машина за мерење жица и каблова").brojZapisnika(this.brojZapisnika)
                .tip(this.oznakaTipa)
                .datum(getDatumFormat())
                .merniOpseg(this.merniOpseg)
                .podnosilacZahteva(this.podnosilacZahteva)
                .serijskiBroj(this.serijskiBroj)
                .osnovneKarakteristike(getOsnovneKarakteristike())
                .pravilnik(this.pravilnik)
                .sluzbenaOznakaTipa(this.sluzbenaOznakaTipa)
                .vrstaKontrolisanja(this.vrstaKontrolisanja)
                .proizvodjac(this.proizvodjac)
                .razlogOdbijanja("RAZLOG")
                .brojZapisnika(this.brojZapisnika)
                .vlasnikKorisnik(this.korisnik)
                .identifikacioniBroj(this.identifikacioniBroj)
                .ispunjavaUslove(this.meriloIspunjavaZahteve)
                .datum2(getDatum2Format())
                .build();

        return meriloHelper;
    }

    private String getDatumFormat(){
        int d = this.datum.getDay();
        int m = this.datum.getMonth() + 1;
        int y = this.datum.getYear() + 1900;
        String date = d + "." + m + "." + y + ".";
        return date;
    }

    private String getDatum2Format(){
        int d = this.datum.getDay();
        int m = this.datum.getMonth() + 1;
        int y = this.datum.getYear() + 1900 + 2;
        String date = d + "." + m + "." + y + ".";
        return date;
    }

    private String getOsnovneKarakteristike(){
        String osnovneKarakteristike = "Температура: " + this.temperatura + "°C; Влажност ваздуха: " + this.vlaznostVazduha + "%; Мерни опсег: " + this.merniOpseg + "; Најмањи подељак скале: " + this.najmanjiPodeljak;
        return osnovneKarakteristike;
    }
}
