package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Zivotinja;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ZooZivotinjeInsertController {

    List<Zivotinja> myList = new ArrayList<>();

    @FXML
    private Button insertAnimalsInZoo;

    @FXML
    private TableColumn<Zivotinja, String> idColumn;

    @FXML
    private TableColumn<Zivotinja, String> imeColumn;

    @FXML
    private TableColumn<Zivotinja, String> vrstaColumn;

    @FXML
    private TableColumn<Zivotinja, String> datumRodenjaColumn;

    @FXML
    private TableColumn<Zivotinja, String> spolColumn;

    @FXML
    private TableView<Zivotinja> tablicaZivotinja;

    @FXML
    private ComboBox<String> insertZooComboBox;


    @FXML
    public void initialize() {
        try{
            Connection veza = Baza.connectToDatabase();

            HelloController.setListaZoo(Baza.dohvatZooIzBaza(veza));

            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        myList.clear();

        ContextMenu contextMenu = new ContextMenu();

        MenuItem addToListMenuItem = new MenuItem("Add to List");

        contextMenu.getItems().add(addToListMenuItem);

        tablicaZivotinja.setContextMenu(contextMenu);

        for(int i=0; i<HelloController.listaZoo.size(); i++){
            insertZooComboBox.getItems().add(HelloController.getListaZoo().get(i).getId().toString());
        }

        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId().toString()));
        imeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIme()));
        vrstaColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVrsta()));
        datumRodenjaColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDatumRodenja().toString()));
        spolColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSpol()));

        ObservableList<Zivotinja> observableListZivotinja = FXCollections.observableList(HelloController.getListaZivotinja());
        tablicaZivotinja.setItems(observableListZivotinja);


        addToListMenuItem.setOnAction(event -> {

            Zivotinja selectedObject = tablicaZivotinja.getSelectionModel().getSelectedItem();

            myList.add(selectedObject);
        });

        tablicaZivotinja.setRowFactory(tv -> {
            TableRow<Zivotinja> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && !row.isEmpty()) {
                    row.getTableView().getSelectionModel().select(row.getIndex());
                }
            });
            return row;
        });
    }

    @FXML
    public void onInsertClick(){
        String zoo = insertZooComboBox.getValue();
        List<Zivotinja> odabraneZivotinje = myList.stream().toList();

        StringBuilder greske = new StringBuilder();

        if(zoo.isEmpty()){
            greske.append("Zoo must choosen!\n");
        }


        if(greske.isEmpty()){
            try {
                for(int i = 0; i<odabraneZivotinje.size(); i++){
                    Baza.umetniNoviZooZivotinje(zoo, odabraneZivotinje.get(i).getId().toString());

                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input was successful");
                alert.setHeaderText("Success!");
                alert.setContentText("Animals added");
                alert.showAndWait();


            } catch (SQLException | IOException e) {
                System.out.println("Greska prilikom dodavanja zivotinja.");
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
