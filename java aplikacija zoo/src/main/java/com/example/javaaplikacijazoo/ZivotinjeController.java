package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Korisnik;
import com.example.entiteti.Zivotinja;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ZivotinjeController {

    @FXML
    private TextField searchNameTextField;

    @FXML
    private TextField searchSpeciesTextField;

    @FXML
    private TextField searchGenderTextField;

    @FXML
    private DatePicker searchDateOfBirthDatePicker;

    @FXML
    private Button searchZivotinjaButton;

    @FXML
    private TableColumn<Zivotinja, String> idColumn;

    @FXML
    private TableColumn<Zivotinja, String> nameCoulmn;

    @FXML
    private TableColumn<Zivotinja, String> speciesColumn;

    @FXML
    private TableColumn<Zivotinja, String> dateOfBirthColumn;

    @FXML
    private TableColumn<Zivotinja, String> genderColumn;

    @FXML
    private TableView<Zivotinja> tablicaZivotinja;


    @FXML
    public void initialize() {
        try{
            Connection veza = Baza.connectToDatabase();

            HelloController.setListaZivotinja(Baza.dohvatZivotinjaIzBaza(veza));

            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId().toString()));
        nameCoulmn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIme()));
        speciesColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVrsta()));
        dateOfBirthColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDatumRodenja().toString()));
        genderColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSpol()));

        ObservableList<Zivotinja> observableListZivotinja = FXCollections.observableList(HelloController.getListaZivotinja());
        tablicaZivotinja.setItems(observableListZivotinja);

    }

    @FXML
    public void searchZivotinja(){
        String searchName = searchNameTextField.getText();
        String searchSpecies = searchSpeciesTextField.getText();
        LocalDate searchDateOfBirth = searchDateOfBirthDatePicker.getValue();
        String searchGender = searchGenderTextField.getText();

        List<Zivotinja> filtriranaListaZivotinja = new ArrayList<>(HelloController.getListaZivotinja());

        if(!searchName.isEmpty()){
            filtriranaListaZivotinja = filtriranaListaZivotinja.stream().filter(
                    s -> s.getIme().toLowerCase().contains(searchName.toLowerCase())).collect(Collectors.toList());
        }

        if(!searchSpecies.isEmpty()){
            filtriranaListaZivotinja = filtriranaListaZivotinja.stream().filter(
                    s -> s.getVrsta().toLowerCase().contains(searchSpecies.toLowerCase())).collect(Collectors.toList());
        }

        if(Optional.ofNullable(searchDateOfBirth).isPresent()){
            filtriranaListaZivotinja = filtriranaListaZivotinja.stream().filter(
                    s -> s.getDatumRodenja().equals(searchDateOfBirth)).collect(Collectors.toList());
        }

        if(!searchGender.isEmpty()){
            filtriranaListaZivotinja = filtriranaListaZivotinja.stream().filter(
                    s -> s.getSpol().toLowerCase().contains(searchGender.toLowerCase())).collect(Collectors.toList());
        }

        ObservableList<Zivotinja> observableListZivotinja = FXCollections.observableList(filtriranaListaZivotinja);
        tablicaZivotinja.setItems(observableListZivotinja);
    }
}
