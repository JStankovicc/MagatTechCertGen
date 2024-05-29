package com.magattech.certGen.service;

public interface BrojZapisnikaService {
    public String getAktuelniBrojZapisnika();
    public void updateZapisnik();

    void checkAndUpdateZapisnik();
}
