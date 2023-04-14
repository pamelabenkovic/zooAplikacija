package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Korisnik;
import com.example.entiteti.Racun;
import com.example.entiteti.Zivotinja;
import com.example.entiteti.Zoo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HelloController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    public static List<Zivotinja> listaZivotinja;
    public static List<Racun> listaRacuna;
    public static List<Korisnik> listaKorisnika;
    public static List<Zoo> listaZoo;

    @FXML
    public void initialize() {
        try{
            Connection veza = Baza.connectToDatabase();

            listaKorisnika = Baza.dohvatKorisnikaIzBaza(veza);
            listaZivotinja = Baza.dohvatZivotinjaIzBaza(veza);
            listaZoo = Baza.dohvatZooIzBaza(veza);
            listaRacuna = Baza.dohvatRacunaIzBaza(veza);

            for(int i = 0; i< listaZoo.size(); i++){
                System.out.println(listaZoo.get(i).getId());
                System.out.println(listaZoo.get(i).getNaziv());
                System.out.println(listaZoo.get(i).getLokacija());
                for(int j = 0; j<listaZoo.get(i).getListaZivotinja().size(); j++){
                    System.out.println(listaZoo.get(i).getListaZivotinja().get(j));
                }
            }
            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    private void onLoginButtonClick() throws IOException {
         String usernameWritten = usernameTextField.getText();
         String passwordWritten = passwordTextField.getText();

         for(int i=0; i<listaKorisnika.size(); i++){
             if(listaKorisnika.get(i).getKorisnickoIme().equals(usernameWritten) &&
                     listaKorisnika.get(i).getLozinka().equals(passwordWritten)){
                 FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("korisnikSearch.fxml"));
                 Scene scene = new Scene(fxmlLoader.load(), 520, 440);
                 HelloApplication.stage.setTitle("Hello!");
                 HelloApplication.stage.setScene(scene);
                 HelloApplication.stage.show();
             }
         }

     }



    public static List<Zivotinja> getListaZivotinja() {
        return listaZivotinja;
    }

    public static void setListaZivotinja(List<Zivotinja> listaZivotinja) {
        HelloController.listaZivotinja = listaZivotinja;
    }

    public static List<Racun> getListaRacuna() {
        return listaRacuna;
    }

    public static void setListaRacuna(List<Racun> listaRacuna) {
        HelloController.listaRacuna = listaRacuna;
    }

    public static List<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }

    public static void setListaKorisnika(List<Korisnik> listaKorisnika) {
        HelloController.listaKorisnika = listaKorisnika;
    }

    public static List<Zoo> getListaZoo() {
        return listaZoo;
    }

    public static void setListaZoo(List<Zoo> listaZoo) {
        HelloController.listaZoo = listaZoo;
    }


}