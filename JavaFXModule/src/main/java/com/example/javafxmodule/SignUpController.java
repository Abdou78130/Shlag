package com.example.javafxmodule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class SignUpController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField idInput;

    @FXML
    private TextField passwordInput;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //Fonction lancée au clic du bouton s'inscrire
    @FXML
    protected void pressButton(ActionEvent event){
        System.out.println("Identifiant : "+idInput.getText());
        System.out.println("Mot de passe : "+passwordInput.getText());
    }


    //Switch sur la fenêtre de connexion au click du bouton "Je suis déjà inscrit"

    public void switchToSignIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("SignIn.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();
    }

}