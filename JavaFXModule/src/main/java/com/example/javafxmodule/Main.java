package com.example.javafxmodule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slack.service.MessageService;
import slack.service.UserService;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserService us = new UserService();
        MessageService ms = new MessageService();


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));

        //fxmlLoader.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //String css = this.getClass().getResource("application.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        //scene.getStylesheets().add(css);
        scene.getStylesheets().add("application.css");

        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}