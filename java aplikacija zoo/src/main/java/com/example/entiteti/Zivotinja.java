package com.example.entiteti;

import java.time.LocalDate;

public class Zivotinja {

    private Long id;
    private String ime;
    private String vrsta;
    private LocalDate datumRodenja;
    private String spol;

    public Zivotinja(Long id, String ime, String vrsta, LocalDate datumRodenja, String spol) {
        this.id = id;
        this.ime = ime;
        this.vrsta = vrsta;
        this.datumRodenja = datumRodenja;
        this.spol = spol;
    }

    public Zivotinja() {

    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public LocalDate getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(LocalDate datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Zivotinja{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", vrsta='" + vrsta + '\'' +
                ", datumRodenja=" + datumRodenja +
                ", spol='" + spol + '\'' +
                '}';
    }
}
