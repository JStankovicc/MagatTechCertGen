package com.magattech.certGen.service;

import java.util.List;

public interface BrojZapisnikaService {
    public String getAktuelniBrojZapisnika();
    public void updateZapisnik();
    public List<String> getAll();
    public List<String> getThisYear();
    public List<String> getWithoutThisYear();
    void checkAndUpdateZapisnik();
}
