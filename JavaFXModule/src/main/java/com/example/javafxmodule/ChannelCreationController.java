package com.example.javafxmodule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import slack.service.ChannelService;

import java.io.IOException;

public class ChannelCreationController {

    @FXML
    private TextField nameInput;

    @FXML
    private Label warning;

    private boolean warningIsDisplayed = false;


    @FXML
    public void channelCreation(ActionEvent event){
        if(!nameInput.getText().isEmpty()){
            ChannelService.creerChannel(nameInput.getText());
            if(!warningIsDisplayed) {
                warning.setText("Le channel à été crée avec succès !");
                warningIsDisplayed = true;
            }
        }
    }

    @FXML
    public void switchToAdminMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("AdminMenu.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();
    }


}
