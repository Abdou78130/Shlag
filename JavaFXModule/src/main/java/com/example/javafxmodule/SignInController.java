package com.example.javafxmodule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import slack.model.User;
import slack.server.Client;
import slack.service.UserService;

import java.io.IOException;
import java.util.Objects;

public class SignInController {

    @FXML
    private TextField idInput;

    @FXML
    private TextField passwordInput;


    //Switch sur la fenêtre d'inscription' au click du bouton "Créer un compte"
    public void switchToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root ,1920,1080);

        stage.setScene(scene);
        stage.show();
    }

    public void signIn(ActionEvent event) throws IOException {
        User user = UserService.authenticate(idInput.getText(), passwordInput.getText());
        if (user != null) {
            Client.connectionServer(UserService.userRepository.select(user.getId()));
        }


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root ,1920,1080);

        stage.setScene(scene);
        stage.show();

    }

}
