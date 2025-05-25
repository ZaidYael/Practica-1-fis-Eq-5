package com.example.fis_practica_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class simulacionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(simulacionApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 620);
        stage.setTitle("Simulador de recorrido");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}