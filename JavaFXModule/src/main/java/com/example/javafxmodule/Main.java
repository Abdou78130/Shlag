package com.example.javafxmodule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sample.fxml"));

        //fxmlLoader.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //String css = this.getClass().getResource("application.css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //scene.getStylesheets().add(css);
        scene.getStylesheets().add("src/main/java/com/example/javafxmodule/application.css");

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}