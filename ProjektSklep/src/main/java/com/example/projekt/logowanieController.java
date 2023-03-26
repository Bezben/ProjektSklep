package com.example.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class logowanieController implements Initializable {

    @FXML
    private PasswordField haslo;

    @FXML
    private TextField nazwa;

    @FXML
    private AnchorPane pane_logowanie;

    @FXML
    private AnchorPane pane_rejestracja;

    @FXML
    private ComboBox rola;

    @FXML
    private TextField wpisz_email;

    @FXML
    private PasswordField wpisz_haslo;

    @FXML
    private TextField wpisz_nazwe;

    @FXML
    private ComboBox wybierz_role;

    @FXML
    private Button zaloguj;

    Connection conn =null;
    ResultSet rs= null;
    PreparedStatement pst= null;

    public void logowaniepokaz(){
        pane_logowanie.setVisible(true);
        pane_rejestracja.setVisible(false);
    }

    public void rejestracjapokaz(){
        pane_logowanie.setVisible(false);
        pane_rejestracja.setVisible(true);
    }

    @FXML


    public void Login(ActionEvent actionEvent) {
        conn = mysqlconnect.ConnectDb();
        String sql = "Select * from users where nazwa = ? and haslo = ? and rola = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,nazwa.getText());
            pst.setString(2,haslo.getText());
            pst.setString(3, rola.getValue().toString());
            rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Pomyślnie zalogowano!");

                //zaloguj.getScene().getWindow().hide();

                if(rola.getValue().toString().equalsIgnoreCase("Admin"))
                {
                    Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    Scene scene = new Scene(root);
                    logowanie.stageMain.setScene(scene);
                    return;
                }
                if(rola.getValue().toString().equalsIgnoreCase("Klient") || rola.getValue().toString().equalsIgnoreCase("Kasjer"))
                {
                    Parent root = FXMLLoader.load(getClass().getResource("sklep.fxml"));
                    Scene scene = new Scene(root);
                    logowanie.stageMain.setScene(scene);
                    return;
                }
            }else
                JOptionPane.showMessageDialog(null, "niepoprawne dane");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Rejestracja(ActionEvent actionEvent){
        conn =mysqlconnect.ConnectDb();
        String sql= "Insert into users (nazwa, haslo, email, rola) values (?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, wpisz_nazwe.getText());
            pst.setString(2, wpisz_haslo.getText());
            pst.setString(3, wpisz_email.getText());
            pst.setString(4, wybierz_role.getValue().toString());
            pst.execute();
            JOptionPane.showMessageDialog(null, "zarejestrowano");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "błąd");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rola.getItems().addAll("Klient","Kasjer","Admin");
        wybierz_role.getItems().addAll("Klient","Kasjer");

    }

}