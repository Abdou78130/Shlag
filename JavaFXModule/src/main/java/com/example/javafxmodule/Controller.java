package com.example.javafxmodule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void pressButton(ActionEvent event){
        System.out.println("Bang ca flingue");
    }
}