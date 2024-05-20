package com.magattech.certGen.model.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MeriloHelper {
    private String nazivMerila;
    private String pravilnik;
    private String podnosilacZahteva;
    private String proizvodjac;
    private String tip;
    private String sluzbenaOznakaTipa;
    private String serijskiBroj;
    private String osnovneKarakteristike;
    private String vlasnikKorisnik;
    private String brojZapisnika;
    private String vrstaKontrolisanja;
    private String datum;
    private String razlogOdbijanja;
    private String merniOpseg;
    private String identifikacioniBroj;
    private boolean ispunjavaUslove;

    @Override
    public String toString() {
        return nazivMerila + " broj " + brojZapisnika;
    }
}
