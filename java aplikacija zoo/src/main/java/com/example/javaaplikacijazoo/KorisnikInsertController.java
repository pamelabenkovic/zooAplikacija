package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class KorisnikInsertController {

    @FXML
    private TextField insertUsernameTextField;

    @FXML
    private TextField insertPasswordTextField;

    @FXML
    private ComboBox<String> insertRoleComboBox;

    @FXML
    private Button insertButoon;

    @FXML
    public void initialize(){
        insertRoleComboBox.getItems().add("admin");
        insertRoleComboBox.getItems().add("gost");
    }

    @FXML
    public void onInsertClick(){
        String username = insertUsernameTextField.getText();
        String password = insertPasswordTextField.getText();
        String role = insertRoleComboBox.getValue();

        StringBuilder greske = new StringBuilder();

        if(username.isEmpty()){
            greske.append("Username must not be empty!\n");
        }

        if(password.isEmpty()){
            greske.append("Password must not be empty!\n");
        }

        if(role.isEmpty()){
            greske.append("Role must be chosen!\n");
        }

        if(greske.isEmpty()){
            try {
                Baza.umetniNovogKorisnika(username, password, role);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input was successful");
                alert.setHeaderText("Success!");
                alert.setContentText("User added");
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
