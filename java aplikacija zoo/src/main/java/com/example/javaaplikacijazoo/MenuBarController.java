package com.example.javaaplikacijazoo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {

    @FXML
    private void onUserSearchButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("korisnikSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("User search");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onAnimalSearchButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zivotinjeSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Animal search");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onZooSearchButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zooSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Zoo search");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onReceiptSearchButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("racunSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Receipt search");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onInsertUserButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("korisnikInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("User insert");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onInsertAnimalButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zivotinjeInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Animal insert");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onInsertReceiptButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("racunInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Receipt insert");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onInsertZooButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zooInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Zoo insert");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

    @FXML
    private void onInsertZooZivotinjeButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zooZivotinjeInsert.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 440);
        HelloApplication.stage.setTitle("Zoo animals insert");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }

}
