package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ZivotinjeInsertController {
    @FXML
    private TextField insertNameTextField;

    @FXML
    private TextField insertSpeciesTextField;

    @FXML
    private DatePicker insertDateOfBirth;

    @FXML
    private ComboBox<String> insertGenderComboBox;

    @FXML
    private Button insertButoon;

    @FXML
    public void initialize(){
        insertGenderComboBox.getItems().add("m");
        insertGenderComboBox.getItems().add("f");
    }

    @FXML
    public void onInsertClick(){
        String name = insertNameTextField.getText();
        String species = insertSpeciesTextField.getText();
        String gender = insertGenderComboBox.getValue();
        LocalDate dateOfBirth = insertDateOfBirth.getValue();

        StringBuilder greske = new StringBuilder();

        if(name.isEmpty()){
            greske.append("Name must not be empty!\n");
        }

        if(species.isEmpty()){
            greske.append("Species must not be empty!\n");
        }

        if(Optional.ofNullable(gender).isEmpty()){
            greske.append("Gender must be chosen!\n");
        }

        if(Optional.ofNullable(dateOfBirth).isEmpty()){
            greske.append("Date of birth must not be empty!\n");
        }

        if(greske.isEmpty()){
            try {
                Baza.umetniNovuZivotinju(name, species, gender, dateOfBirth);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input was successful");
                alert.setHeaderText("Success!");
                alert.setContentText("Animal added");
                alert.showAndWait();

            } catch (SQLException | IOException e) {
                System.out.println("Greska prilikom dodavanja novog korisnika.");
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
