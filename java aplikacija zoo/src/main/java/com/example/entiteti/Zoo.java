package com.example.entiteti;

import java.util.List;

public class Zoo {

    private Long id;
    private String naziv;
    private String lokacija;
    private List<Zivotinja> listaZivotinja;

    public Zoo(Long id, String naziv, String lokacija, List<Zivotinja> listaZivotinja) {
        this.id = id;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.listaZivotinja = listaZivotinja;
    }

    public Zoo() {

    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public List<Zivotinja> getListaZivotinja() {
        return listaZivotinja;
    }

    public void setListaZivotinja(List<Zivotinja> listaZivotinja) {
        this.listaZivotinja = listaZivotinja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", lokacija='" + lokacija + '\'' +
                ", listaZivotinja=" + listaZivotinja +
                '}';
    }
}
