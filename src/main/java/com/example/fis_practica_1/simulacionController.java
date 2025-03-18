package com.example.fis_practica_1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class simulacionController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}