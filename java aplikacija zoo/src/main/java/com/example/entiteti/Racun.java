package com.example.entiteti;

import java.time.LocalDateTime;

public class Racun {

    private Long id;
    private String sifraRacuna;
    private Korisnik korisnik;
    private LocalDateTime vrijemeIzdavanja;
    private Zivotinja zivotinja;

    public Racun(Long id, String sifraRacuna, Korisnik korisnik, LocalDateTime vrijemeIzdavanja, Zivotinja zivotinja) {
        this.id = id;
        this.sifraRacuna = sifraRacuna;
        this.korisnik = korisnik;
        this.vrijemeIzdavanja = vrijemeIzdavanja;
        this.zivotinja = zivotinja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifraRacuna() {
        return sifraRacuna;
    }

    public void setSifraRacuna(String sifraRacuna) {
        this.sifraRacuna = sifraRacuna;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public LocalDateTime getVrijemeIzdavanja() {
        return vrijemeIzdavanja;
    }

    public void setVrijemeIzdavanja(LocalDateTime vrijemeIzdavanja) {
        this.vrijemeIzdavanja = vrijemeIzdavanja;
    }

    public Zivotinja getZivotinja() {
        return zivotinja;
    }

    public void setZivotinja(Zivotinja zivotinja) {
        this.zivotinja = zivotinja;
    }
}
