package com.example.javaaplikacijazoo;

import com.example.data.Baza;
import com.example.entiteti.Korisnik;
import com.example.entiteti.Zoo;
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

public class ZooController {

    @FXML
    private TextField searchNameTextField;

    @FXML
    private TextField searchLocationTextField;

    @FXML
    private Button searchZooButton;

    @FXML
    private TableColumn<Zoo, String> idColumn;

    @FXML
    private TableColumn<Zoo, String> nameColumn;

    @FXML
    private TableColumn<Zoo, String> locationColumn;

    @FXML
    private TableColumn<Zoo, String> zivotinjeColumn;

    @FXML
    private TableView<Zoo> tablicaZoo;


    @FXML
    public void initialize() {
        try{
            Connection veza = Baza.connectToDatabase();

            HelloController.setListaZoo(Baza.dohvatZooIzBaza(veza));

            veza.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId().toString()));
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNaziv()));
        locationColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLokacija()));
        zivotinjeColumn.setCellValueFactory(c -> {
            StringBuilder zivotinje = new StringBuilder();
            if(c.getValue().getListaZivotinja().isEmpty()){
                zivotinje.append("nisu dodane");
            }else{
                for(int i = 0; i<c.getValue().getListaZivotinja().size(); i++){
                    zivotinje.append(c.getValue().getListaZivotinja().get(i).getIme()+", ");
                }
                zivotinje= new StringBuilder(zivotinje.substring(0, zivotinje.length() - 2));
            }

            return new SimpleStringProperty(zivotinje.toString());
        });

        ObservableList<Zoo> observableListZoo = FXCollections.observableList(HelloController.getListaZoo());
        tablicaZoo.setItems(observableListZoo);

    }

    @FXML
    public void searchZoo(){
        String searchName = searchNameTextField.getText();
        String searchLocation = searchLocationTextField.getText();

        List<Zoo> filtriranaListaZoo = new ArrayList<>(HelloController.getListaZoo());

        if(!searchName.isEmpty()){
            filtriranaListaZoo = filtriranaListaZoo.stream().filter(
                    s -> s.getNaziv().toLowerCase().contains(searchName.toLowerCase())).collect(Collectors.toList());
        }

        if(!searchLocation.isEmpty()){
            filtriranaListaZoo = filtriranaListaZoo.stream().filter(
                    s -> s.getLokacija().toLowerCase().contains(searchLocation.toLowerCase())).collect(Collectors.toList());
        }

        ObservableList<Zoo> observableListZoo = FXCollections.observableList(filtriranaListaZoo);
        tablicaZoo.setItems(observableListZoo);
    }

}
