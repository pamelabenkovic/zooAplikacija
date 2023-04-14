package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Korisnik;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KorisnikController {

    @FXML
    private TextField searchUsernameTextField;

    @FXML
    private TextField searchRoleTextField;

    @FXML
    private Button searchKorisnikButton;

    @FXML
    private TableColumn<Korisnik, String> idColumn;

    @FXML
    private TableColumn<Korisnik, String> usernameCoulmn;

    @FXML
    private TableColumn<Korisnik, String> roleColumn;

    @FXML
    private TableView<Korisnik> tablicaKorisnika;


    @FXML
    public void initialize() {
        try{
            Connection veza = Baza.connectToDatabase();

            HelloController.setListaKorisnika(Baza.dohvatKorisnikaIzBaza(veza));

            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId().toString()));
        usernameCoulmn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getKorisnickoIme()) );
        roleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUloga()));

        ObservableList<Korisnik> observableListKorisnik = FXCollections.observableList(HelloController.getListaKorisnika());
        tablicaKorisnika.setItems(observableListKorisnik);

    }

    @FXML
    public void searchKorisnike(){
        String searchUsername = searchUsernameTextField.getText();
        String searchRole = searchRoleTextField.getText();

        List<Korisnik> filtriranaListaKorisnika = new ArrayList<>(HelloController.getListaKorisnika());

        if(!searchUsername.isEmpty()){
            filtriranaListaKorisnika = filtriranaListaKorisnika.stream().filter(
                    s -> s.getKorisnickoIme().toLowerCase().contains(searchUsername.toLowerCase())).collect(Collectors.toList());
        }

        if(!searchRole.isEmpty()){
            filtriranaListaKorisnika = filtriranaListaKorisnika.stream().filter(
                    s -> s.getUloga().toLowerCase().contains(searchRole.toLowerCase())).collect(Collectors.toList());
        }

        ObservableList<Korisnik> observableListKorisnik = FXCollections.observableList(filtriranaListaKorisnika);
        tablicaKorisnika.setItems(observableListKorisnik);
    }

}
