package com.magattech.certGen.model.merila;

import com.magattech.certGen.model.additional.MernaTrakaSaViskom25mND;
import com.magattech.certGen.model.helper.MeriloHelper;
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
@Table(name = "_merna_traka_sa_viskom_25m")
public class MernaTrakaSaViskom25m {
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
    private String odstupanje11;
    @Column(length = 10)
    private String odstupanje12;
    @Column(length = 10)
    private String odstupanje13;
    @Column(length = 10)
    private String odstupanje14;
    @Column(length = 10)
    private String odstupanje15;
    @Column(length = 10)
    private String odstupanje16;
    @Column(length = 10)
    private String odstupanje17;
    @Column(length = 10)
    private String odstupanje18;
    @Column(length = 10)
    private String odstupanje19;
    @Column(length = 10)
    private String odstupanje20;
    @Column(length = 10)
    private String odstupanje21;
    @Column(length = 10)
    private String odstupanje22;
    @Column(length = 10)
    private String odstupanje23;
    @Column(length = 10)
    private String odstupanje24;
    @Column(length = 10)
    private String odstupanje25;
    @Column(length = 10)
    private String odstupanje26;


    @Column(length = 10)
    private String ndg1;
    @Column(length = 10)
    private String ndg2;
    @Column(length = 10)
    private String ndg3;
    @Column(length = 10)
    private String ndg4;
    @Column(length = 10)
    private String ndg5;
    @Column(length = 10)
    private String ndg6;
    @Column(length = 10)
    private String ndg7;
    @Column(length = 10)
    private String ndg8;
    @Column(length = 10)
    private String ndg9;
    @Column(length = 10)
    private String ndg10;
    @Column(length = 10)
    private String ndg11;
    @Column(length = 10)
    private String ndg12;
    @Column(length = 10)
    private String ndg13;
    @Column(length = 10)
    private String ndg14;
    @Column(length = 10)
    private String ndg15;
    @Column(length = 10)
    private String ndg16;
    @Column(length = 10)
    private String ndg17;
    @Column(length = 10)
    private String ndg18;
    @Column(length = 10)
    private String ndg19;
    @Column(length = 10)
    private String ndg20;
    @Column(length = 10)
    private String ndg21;
    @Column(length = 10)
    private String ndg22;
    @Column(length = 10)
    private String ndg23;
    @Column(length = 10)
    private String ndg24;
    @Column(length = 10)
    private String ndg25;
    @Column(length = 10)
    private String ndg26;

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
    private String ndg27;
    @Column(length = 10)
    private String ndr1;

    private String brojMernogLenjira;
    private String brojMerneLupe;

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

    private String unit1;
    private String unit2;

    private String propisaniZahtevi;

    private String razlogOdbijanja;

    public MernaTrakaSaViskom25mND getMernaTrakaSaViskom25mND(){
        MernaTrakaSaViskom25mND mernaTrakaSaViskom25mND = MernaTrakaSaViskom25mND.builder().ndg1(this.ndg1).ndg2(this.ndg2).ndg3(this.ndg3).ndg4(this.ndg4)
                .ndg5(this.ndg5).ndg6(this.ndg6).ndg7(this.ndg7).ndg8(this.ndg8).ndg9(this.ndg9).ndg10(this.ndg10).ndg11(this.ndg11).ndg12(this.ndg12)
                .ndg13(this.ndg13).ndg14(this.ndg14).ndg15(this.ndg15).ndg16(this.ndg16).ndg17(this.ndg17).ndg18(this.ndg18).ndg19(this.ndg19).ndg20(this.ndg20)
                .ndg21(this.ndg21).ndg22(this.ndg22).ndg23(this.ndg23).ndg24(this.ndg24).ndg25(this.ndg25).ndg26(this.ndg26).ndg27(this.ndg27).ndr1(this.ndr1).build();
        return mernaTrakaSaViskom25mND;
    }

    public MeriloHelper getMeriloHeplper(){
        MeriloHelper meriloHelper = MeriloHelper.builder().nazivMerila("Мерна трака с виском").brojZapisnika(this.brojZapisnika)
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
