package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sklep","root","");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<artykuly> getDataartykuly(){
        Connection conn= ConnectDb();
        ObservableList<artykuly> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from artykuly");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(
                        new artykuly(
                                Integer.parseInt(rs.getString("id")),
                                Integer.parseInt(rs.getString("cena")),
                                Integer.parseInt(rs.getString("cena_app")),
                                rs.getString("nazwa"),
                                rs.getString("ilosc"))
                );
            }
        }catch (Exception e){

        }
        return list;
    }
}
