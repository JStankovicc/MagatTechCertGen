package com.magattech.certGen.model.merila;

import com.magattech.certGen.model.User;
import com.magattech.certGen.model.additional.JednodelnoMeriloND;
import com.magattech.certGen.model.helper.MeriloHelper;
import com.magattech.certGen.model.included.VrstaKontrolisanja;
import com.magattech.certGen.model.request.JednodelnoMeriloRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
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
    @Column(length = 10)
    private String klasaTacnosti;

    private String temperatura;
    private String vlaznostVazduha;

    private boolean meriloJeIspravno;

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

    @Column(length = 15)
    private String greska1;
    @Column(length = 15)
    private String greska2;
    @Column(length = 15)
    private String greska3;
    @Column(length = 15)
    private String greska4;
    @Column(length = 15)
    private String greska5;
    @Column(length = 15)
    private String greska6;
    @Column(length = 15)
    private String greska7;
    @Column(length = 15)
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

    @Column(length = 15)
    private String greska9;
    @Column(length = 15)
    private String greska10;
    @Column(length = 15)
    private String greska11;
    @Column(length = 15)
    private String greska12;
    @Column(length = 15)
    private String greska13;
    @Column(length = 15)
    private String greska14;
    @Column(length = 15)
    private String greska15;
    @Column(length = 15)
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

    private String pravilnik;
    private boolean meriloIspunjavaZahteve;

    private String komentar2;
    
    private Date datum;

    private String etalonirao;
    private String odobrio;

    private String razlogOdbijanja;

    private boolean odobreno;

    private String unit1;
    private String unit2;
    private String unit3;
    private String unit4;

    private String propisaniZahtevi;

    public JednodelnoMeriloND getJednodelnoMeriloND(){
        JednodelnoMeriloND jednodelnoMeriloND = JednodelnoMeriloND.builder().ndg1(this.ndg1).ndg2(this.ndg2)
                .ndg3(this.ndg3).ndg4(this.ndg4).ndr1(this.ndr1).ndr2(this.ndr2).build();
        return jednodelnoMeriloND;
    }

    public MeriloHelper getMeriloHeplper(){
        MeriloHelper meriloHelper = MeriloHelper.builder().nazivMerila("Мерни лењир (Једноделно мерило дужине)").brojZapisnika(this.brojZapisnika)
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
                .razlogOdbijanja(this.razlogOdbijanja)
                .brojZapisnika(this.brojZapisnika)
                .vlasnikKorisnik(this.korisnik)
                .identifikacioniBroj(this.identifikacioniBroj)
                .ispunjavaUslove(this.meriloIspunjavaZahteve)
                .datum2(getDatum2Format())
                .build();

        return meriloHelper;
    }

    private String getDatumFormat(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(this.datum);
//        int d = this.datum.getDay();
//        int m = this.datum.getMonth() + 1;
//        int y = this.datum.getYear() + 1900;
//        String date = d + "." + m + "." + y + ".";
//        return date;
    }

    private String getDatum2Format(){
        int d = 31;
        int m = 12;
        int y = this.datum.getYear() + 1900 + 2;
        String date = d + "." + m + "." + y + ".";
        return date;
    }

    private String getOsnovneKarakteristike(){
        String osnovneKarakteristike = "Мерни опсег: " + this.merniOpseg + "; Најмањи подељак скале:trenutak " + this.najmanjiPodeljak + "; Класа тачности: " + this.klasaTacnosti;
        return osnovneKarakteristike;
    }

}
