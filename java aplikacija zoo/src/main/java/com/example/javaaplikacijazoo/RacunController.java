package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Korisnik;
import com.example.entiteti.Racun;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RacunController {

    @FXML
    private TextField searchReceiptNumberTextField;

    @FXML
    private TextField searchUserIdTextField;

    @FXML
    private TextField searchAnimalIdTextField;

    @FXML
    private DatePicker searchDateTextField;

    @FXML
    private Button searchReceipts;

    @FXML
    private TableColumn<Racun, String> idColumn;

    @FXML
    private TableColumn<Racun, String> receiptNumberCoulmn;

    @FXML
    private TableColumn<Racun, String> userIdColumn;

    @FXML
    private TableColumn<Racun, String> animalIdColumn;

    @FXML
    private TableColumn<Racun, String> receiptDateIdColumn;

    @FXML
    private TableView<Racun> tablicaRacuna;


    @FXML
    public void initialize() {
        try{
            Connection veza = Baza.connectToDatabase();

            HelloController.setListaRacuna(Baza.dohvatRacunaIzBaza(veza));

            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId().toString()));
        receiptNumberCoulmn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSifraRacuna()));
        userIdColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getKorisnik().getId().toString()));
        animalIdColumn.setCellValueFactory(c -> {
            StringBuilder stringBuilder= new StringBuilder();
            stringBuilder.append(c.getValue().getZivotinja().getId().toString()+" "+ c.getValue().getZivotinja().getIme());
            return new SimpleStringProperty(stringBuilder.toString());
        });
        receiptDateIdColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVrijemeIzdavanja().toString()));


        ObservableList<Racun> observableListRacun = FXCollections.observableList(HelloController.getListaRacuna());
        tablicaRacuna.setItems(observableListRacun);

    }

    @FXML
    public void searchRacun(){
        String searchReceiptNumber = searchReceiptNumberTextField.getText();
        String searchUserId = searchUserIdTextField.getText();
        String searchAnimalId = searchAnimalIdTextField.getText();
        LocalDate searchDateId = searchDateTextField.getValue();

        List<Racun> filtriranaListaRacuna = new ArrayList<>(HelloController.getListaRacuna());

        if(!searchReceiptNumber.isEmpty()){
            filtriranaListaRacuna = filtriranaListaRacuna.stream().filter(
                    s -> s.getSifraRacuna().toLowerCase().contains(searchReceiptNumber.toLowerCase())).collect(Collectors.toList());
        }

        if(!searchUserId.isEmpty()){
            filtriranaListaRacuna = filtriranaListaRacuna.stream().filter(
                    s -> s.getKorisnik().getId().toString().equals(searchUserId)).collect(Collectors.toList());
        }

        if(!searchAnimalId.isEmpty()){
            filtriranaListaRacuna = filtriranaListaRacuna.stream().filter(
                    s -> s.getZivotinja().getId().toString().contains(searchAnimalId)).collect(Collectors.toList());
        }

        if(Optional.ofNullable(searchDateId).isPresent()){
            filtriranaListaRacuna = filtriranaListaRacuna.stream().filter(
                    s -> s.getVrijemeIzdavanja().toLocalDate().equals(searchDateId)).collect(Collectors.toList());
        }



        ObservableList<Racun> observableListRacun = FXCollections.observableList(filtriranaListaRacuna);
        tablicaRacuna.setItems(observableListRacun);
    }

}
