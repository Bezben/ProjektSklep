package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class artykulyController implements Initializable {
    @FXML
    private TableView<artykuly> tab;
    @FXML
    private TableColumn<artykuly, Integer> tab_cena;

    @FXML
    private TableColumn<artykuly, Integer> tab_cenaapp;

    @FXML
    private TableColumn<artykuly, Integer> tab_id;

    @FXML
    private TableColumn<artykuly, String> tab_limit;

    @FXML
    private TableColumn<artykuly, String> tab_produkt;

    //public void logout(ActionEvent event) throws IOException {
    public void logout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        logowanie.stageMain.setScene(scene);
        return;
    }

    ObservableList<artykuly> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_produkt.setCellValueFactory(new PropertyValueFactory<artykuly, String>("nazwa"));
        tab_cena.setCellValueFactory(new PropertyValueFactory<artykuly, Integer>("cena"));
        tab_cenaapp.setCellValueFactory(new PropertyValueFactory<artykuly, Integer>("cena_app"));
        tab_limit.setCellValueFactory(new PropertyValueFactory<artykuly, String>("ilosc"));

        listM = mysqlconnect.getDataartykuly();

        /*ObservableList<artykuly> test = FXCollections.observableArrayList(
                new artykuly(1, 2, 4, "asd", "asd")
        );*/
        tab.setItems(listM);

        //tab.getItems().addAll(listM);
    }
}
