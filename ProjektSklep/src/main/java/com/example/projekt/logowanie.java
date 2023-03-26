package com.example.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class logowanie extends Application {

    public static Stage stageMain;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logowanie.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // zapis stage do static zeby kazda inna klasa mogla pozniej zmienic okno np. komenda logowanie.stageMain.[jakas_czynnosc]
        stageMain = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}