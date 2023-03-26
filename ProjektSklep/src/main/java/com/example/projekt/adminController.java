package com.example.projekt;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class adminController implements Initializable {
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

    @FXML
    private TextField w_cena;

    @FXML
    private TextField w_cenaapp;

    @FXML
    private TextField w_limit;

    @FXML
    private TextField w_produkt;

    @FXML
    private TextField w_id;

    public void aktualizuj(){
        tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_produkt.setCellValueFactory(new PropertyValueFactory<artykuly, String>("nazwa"));
        tab_cena.setCellValueFactory(new PropertyValueFactory<artykuly, Integer>("cena"));
        tab_cenaapp.setCellValueFactory(new PropertyValueFactory<artykuly, Integer>("cena_app"));
        tab_limit.setCellValueFactory(new PropertyValueFactory<artykuly, String>("ilosc"));

        listM = mysqlconnect.getDataartykuly();
        tab.setItems(listM);
    }

    public void Dodaj(ActionEvent actionEvent){
        conn =mysqlconnect.ConnectDb();
        String sql= "Insert into artykuly (id, nazwa, cena, cena_app, ilosc) values (?,?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,w_id.getText());
            pst.setString(2, w_produkt.getText());
            pst.setString(3, w_cena.getText());
            pst.setString(4, w_cenaapp.getText());
            pst.setString(5, w_limit.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dodano");
            aktualizuj();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "błąd");
        }
    }

    public void Usun(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from artykuly where id =?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, w_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usunięto");
            aktualizuj();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "BŁąd");
        }
    }



    public void Edycja(){
        try{
            conn = mysqlconnect.ConnectDb();
            String value0=w_id.getText();
            String value1=w_produkt.getText();
            String value2=w_cena.getText();
            String value3=w_cenaapp.getText();
            String value4=w_limit.getText();
            String sql = "update artykuly set id='"+value0+"', nazwa= '"+value1+"', cena= '"+value2+"', cena_app= '"+value3+"', ilosc= '"+value4+"'where id= '"+value0+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "zaktualizowoano");
            aktualizuj();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "błąd");
        }
    }
    public void Wyloguj(ActionEvent event) throws IOException {
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
        aktualizuj();
    }

    public void wybierz(javafx.scene.input.MouseEvent mouseEvent) {
            index = tab.getSelectionModel().getSelectedIndex();
            if(index <=-1){
                return;
            }
            w_id.setText(tab_id.getCellData(index).toString());
            w_produkt.setText(tab_produkt.getCellData(index).toString());
            w_cena.setText(tab_cena.getCellData(index).toString());
            w_cenaapp.setText(tab_cenaapp.getCellData(index).toString());
            w_limit.setText(tab_limit.getCellData(index).toString());


        }
    }

