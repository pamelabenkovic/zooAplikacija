package com.example.data;

import com.example.entiteti.Korisnik;
import com.example.entiteti.Racun;
import com.example.entiteti.Zivotinja;
import com.example.entiteti.Zoo;
import com.example.javaaplikacijazoo.HelloController;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Baza {

    public static Connection  connectToDatabase() throws SQLException, IOException{
        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/data.properties"));

        String databaseURL = configuration.getProperty("databaseURL");
        String databaseKorisnickoIme = configuration.getProperty("databaseKorisnickoIme");
        String databaseLozinka = configuration.getProperty("databaseLozinka");

        Connection veza = DriverManager.getConnection(databaseURL, databaseKorisnickoIme, databaseLozinka);

        return veza;
    }

    public static List<Korisnik> dohvatKorisnikaIzBaza(Connection veza) throws SQLException {
        List<Korisnik> listaKorisnika= new ArrayList<>();

        Statement noviStatment= veza.createStatement();

        ResultSet setKorisnika= noviStatment.executeQuery("SELECT * FROM KORISNIK");

        while(setKorisnika.next()){

            Long id= setKorisnika.getLong("ID");
            String korisnickoIme = setKorisnika.getString("KORISNICKOIME");
            String lozinka = setKorisnika.getString("LOZINKA");
            String uloga= setKorisnika.getString("ULOGA");

           Korisnik korisnik = new Korisnik(id, korisnickoIme, lozinka, uloga);

            listaKorisnika.add(korisnik);
        }

        return listaKorisnika;
    }

    public static List<Zivotinja> dohvatZivotinjaIzBaza(Connection veza) throws SQLException {
        List<Zivotinja> listaZivotinja= new ArrayList<>();

        Statement noviStatment= veza.createStatement();

        ResultSet setZivotinja= noviStatment.executeQuery("SELECT * FROM ZIVOTINJA");

        while(setZivotinja.next()){

            Long id= setZivotinja.getLong("ID");
            String ime= setZivotinja.getString("IME");
            String vrsta = setZivotinja.getString("VRSTA");
            LocalDate datumRodenja = setZivotinja.getDate("DATUMRODENJA").toLocalDate();
            String spol = setZivotinja.getString("SPOL");

            Zivotinja zivotinja= new Zivotinja(id, ime, vrsta, datumRodenja, spol);

            listaZivotinja.add(zivotinja);
        }

        return listaZivotinja;
    }

    public static List<Racun> dohvatRacunaIzBaza(Connection veza) throws SQLException {
        List<Racun> listaRacuna= new ArrayList<>();

        Statement noviStatment= veza.createStatement();

        ResultSet setRacuna= noviStatment.executeQuery("SELECT * FROM RACUN");

        while(setRacuna.next()){

            Long id= setRacuna.getLong("ID");
            String sifraRacuna= setRacuna.getString("SIFRARACUNA");
            Long idKorisnika = setRacuna.getLong("IDKORISNIK");
            Long idZivotinje = setRacuna.getLong("IDZIVOTINJE");
            LocalDate datumIzdavanja= setRacuna.getDate("DATUMIZDAVANJA").toLocalDate();
            String vrijemeIzdavanja = setRacuna.getString("VRIJEMEIZDAVANJA");

            String datumIVrijemeString = datumIzdavanja.toString()+ " " +vrijemeIzdavanja.toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(datumIVrijemeString, formatter);

            Zivotinja odabranaZivotinja = new Zivotinja();
            for(int i=0; i< HelloController.listaZivotinja.size(); i++){
                if(idZivotinje.equals(HelloController.listaZivotinja.get(i).getId())){
                   odabranaZivotinja = HelloController.listaZivotinja.get(i);
                }
            }

            Korisnik odabraniKorisnik = new Korisnik();
            List<Korisnik> listaKorisnika = HelloController.listaKorisnika;
            for(int i=0; i< listaKorisnika.size(); i++){
                if(idKorisnika.equals(listaKorisnika.get(i).getId())){
                    odabraniKorisnik = listaKorisnika.get(i);
                }
            }

            Racun racun= new Racun(id, sifraRacuna, odabraniKorisnik, dateTime, odabranaZivotinja );
            listaRacuna.add(racun);
        }

        return listaRacuna;
    }

    public static List<Zoo> dohvatZooIzBaza(Connection veza) throws SQLException {
        List<Zoo> listaZoo= new ArrayList<>();

        Statement noviStatment= veza.createStatement();

        ResultSet setZoo= noviStatment.executeQuery("SELECT * FROM ZOO");

        while(setZoo.next()){

            Long id= setZoo.getLong("ID");
            String naziv= setZoo.getString("NAZIV");
            String lokacija = setZoo.getString("LOKACIJA");

            PreparedStatement izjava= veza.prepareStatement("SELECT * FROM ZOOZIVOTINJE WHERE IDZOO = ?");
            izjava.setLong(1,id);

            ResultSet setZivotinjaZaZoo = izjava.executeQuery();
            List<Zivotinja> listaZivotinjaZaZoo= new ArrayList<>();

            while(setZivotinjaZaZoo.next()){

                Long idZoo= setZivotinjaZaZoo.getLong("IDZOO");
                Long idZivotinja = setZivotinjaZaZoo.getLong("IDZIVOTINJA");

                for(int i=0; i< HelloController.listaZivotinja.size(); i++){
                    if(idZivotinja.equals(HelloController.listaZivotinja.get(i).getId())){
                        listaZivotinjaZaZoo.add(HelloController.listaZivotinja.get(i));
                    }
                }

            }

            Zoo zoo = new Zoo(id, naziv, lokacija,  listaZivotinjaZaZoo);
            listaZoo.add(zoo);



        }

        return listaZoo;
    }

    public static void umetniNovogKorisnika(String username, String password, String role) throws SQLException, IOException {
        Connection veza= connectToDatabase();
        PreparedStatement izjava= veza.prepareStatement("INSERT INTO KORISNIK(KORISNICKOIME, LOZINKA, ULOGA) VALUES(?, ?, ?)");
        izjava.setString(1,username);
        izjava.setString(2,password);
        izjava.setString(3,role);

        izjava.execute();

        veza.close();
    }

    public static void umetniNovuZivotinju(String name, String species, String gender, LocalDate dateOfBirth) throws SQLException, IOException {
        Connection veza= connectToDatabase();
        PreparedStatement izjava= veza.prepareStatement("INSERT INTO ZIVOTINJA(IME, VRSTA, DATUMRODENJA, SPOL) VALUES(?, ?, ?, ?)");
        izjava.setString(1,name);
        izjava.setString(2,species);
        izjava.setString(3,dateOfBirth.toString());
        izjava.setString(4,gender);


        izjava.execute();

        veza.close();
    }

    public static void umetniNoviRacun(String receiptNumber, String user, String animal, LocalDate receiptDate) throws SQLException, IOException {
        Connection veza= connectToDatabase();
        PreparedStatement izjava= veza.prepareStatement("INSERT INTO RACUN(SIFRARACUNA, IDKORISNIK, IDZIVOTINJE, DATUMIZDAVANJA, VRIJEMEIZDAVANJA) VALUES(?, ?, ?, ?, ?)");
        izjava.setString(1,receiptNumber);
        izjava.setString(2,user);
        izjava.setString(3,animal);
        izjava.setString(4,receiptDate.toString());

        LocalTime time = LocalTime.now();
        String t = time.format(DateTimeFormatter.ofPattern("HH:mm"));

        izjava.setString(5, t);


        izjava.execute();

        veza.close();
    }

    public static void umetniNoviZoo(String name, String location) throws SQLException, IOException {
        Connection veza= connectToDatabase();
        PreparedStatement izjava= veza.prepareStatement("INSERT INTO ZOO(NAZIV, LOKACIJA) VALUES(?, ?)");
        izjava.setString(1,name);
        izjava.setString(2,location);

        izjava.execute();

        veza.close();
    }

    public static void umetniNoviZooZivotinje(String id1, String id2) throws SQLException, IOException {
        Connection veza= connectToDatabase();
        PreparedStatement izjava= veza.prepareStatement("INSERT INTO ZOOZIVOTINJE(IDZOO, IDZIVOTINJA) VALUES(?, ?)");
        izjava.setString(1,id1);
        izjava.setString(2,id2);

        izjava.execute();

        veza.close();
    }
}
