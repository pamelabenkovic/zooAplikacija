package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ZooInsertController {

    @FXML
    private TextField insertNameTextField;

    @FXML
    private TextField insertLocationTextField;

    @FXML
    private Button insertButoon;



    @FXML
    public void initialize(){
        try{
            Connection veza = Baza.connectToDatabase();

            HelloController.setListaZoo(Baza.dohvatZooIzBaza(veza));

            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void onInsertClick(){
        String name = insertNameTextField.getText();
        String location = insertLocationTextField.getText();

        StringBuilder greske = new StringBuilder();

        if(name.isEmpty()){
            greske.append("Name must not be empty!\n");
        }

        if(location.isEmpty()){
            greske.append("Location must not be empty!\n");
        }


        if(greske.isEmpty()){
            try {
                Baza.umetniNoviZoo(name, location);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input was successful");
                alert.setHeaderText("Success!");
                alert.setContentText("Zoo added");
                alert.showAndWait();

            } catch (SQLException | IOException e) {
                System.out.println("Greska prilikom dodavanja novog zooloskog.");
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
