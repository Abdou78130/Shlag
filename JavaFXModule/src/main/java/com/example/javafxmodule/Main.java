package com.example.javafxmodule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import slack.model.Message;
import slack.model.User;
import slack.service.MessageService;
import slack.service.UserService;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Partie code

        User u = UserService.inscription("saren", "mdp", "saren@gmail.com", "Mastier", "Lucas");
        User u2 = UserService.inscription("abdou78", "mdp", "abdou78@gmail.com", "Haba", "Abdallah");

        List<User> list = UserService.userRepository.select();
        List<Message> list_mess = MessageService.messageRepository.select();





        //Partie IHM
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));

        //fxmlLoader.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //String css = this.getClass().getResource("application.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        //scene.getStylesheets().add(css);
        scene.getStylesheets().add("application.css");

        Image image = new Image(Main.class.getResourceAsStream("ShlagLogo.png"));
        stage.getIcons().add(image);
        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}