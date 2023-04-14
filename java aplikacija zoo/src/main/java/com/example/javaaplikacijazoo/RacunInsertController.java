package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Korisnik;
import com.example.entiteti.Zivotinja;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class RacunInsertController {
    @FXML
    private TextField insertReceiptNumberTextField;

    @FXML
    private ComboBox<String> insertUserComboBox;

    @FXML
    private ComboBox<String> insertAnimalComboBox;

    @FXML
    private DatePicker insertDateOfBirthDatePicker;

    @FXML
    private Button insertButoon;

    @FXML
    public void initialize(){
        for(int i = 0; i<HelloController.listaKorisnika.size(); i++){
            insertUserComboBox.getItems().add(HelloController.getListaKorisnika().get(i).getId().toString() + " " + HelloController.getListaKorisnika().get(i).getKorisnickoIme());
        }

        for(int i = 0; i<HelloController.listaZivotinja.size(); i++){
            insertAnimalComboBox.getItems().add(HelloController.getListaZivotinja().get(i).getId().toString() + " " + HelloController.getListaZivotinja().get(i).getIme());
        }


    }

    @FXML
    public void onInsertClick(){
        String receiptNumber = insertReceiptNumberTextField.getText();
        String user = insertUserComboBox.getValue();
        String animal = insertAnimalComboBox.getValue();
        LocalDate receiptDate = insertDateOfBirthDatePicker.getValue();

        StringBuilder greske = new StringBuilder();

        if(receiptNumber.isEmpty()){
            greske.append("Receipt number must not be empty!\n");
        }

        Integer indexUser = 0;
        if(Optional.ofNullable(user).isEmpty()){
            greske.append("User must be chosen!\n");
        }else{
            indexUser= insertUserComboBox.getSelectionModel().getSelectedIndex();
        }

        Integer indexAnimal= 0;
        if(Optional.ofNullable(animal).isEmpty()){
            greske.append("Animal must be chosen!\n");
        }else{
            indexAnimal= insertAnimalComboBox.getSelectionModel().getSelectedIndex();
        }

        if(Optional.ofNullable(receiptDate).isEmpty()){
            greske.append("Role must be chosen!\n");
        }


        if(greske.isEmpty()){
            try {
                Korisnik korisnik = HelloController.getListaKorisnika().get(indexUser);
                Zivotinja zivotinja = HelloController.getListaZivotinja().get(indexAnimal);

                Baza.umetniNoviRacun(receiptNumber, korisnik.getId().toString(), zivotinja.getId().toString(), receiptDate);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input was successful");
                alert.setHeaderText("Success!");
                alert.setContentText("Receipt added");
                alert.showAndWait();

            } catch (SQLException | IOException e) {
                System.out.println("Greska prilikom dodavanja novog racuna.");
                e.printStackTrace();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input not successful");
            alert.setHeaderText("Error!");
            alert.setContentText(greske.toString());
            alert.showAndWait();
        }
    }
}
