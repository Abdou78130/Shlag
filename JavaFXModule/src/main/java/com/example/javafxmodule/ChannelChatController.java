package com.example.javafxmodule;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slack.server.Client;
import slack.service.ChannelService;
import slack.service.UserService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ChannelChatController {

    @FXML
    private TextArea chatArea;

    @FXML
    private static TextField messageInput = new TextField("test");

    @FXML
    private TilePane messageInputPane;

    private static ByteArrayInputStream message;

    private Service<Void> backgroundThread;

    @FXML
    private Label titleChannel;

    static {
        try {
            message = new ByteArrayInputStream(messageInput.getText().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        titleChannel.setText("Vous êtes connecté au "+ChannelService.getCurrentChannel());
        messageInput.setPrefSize( 1650, 29 );
        messageInput.setOnAction(new EventHandler() {
            public void handle(Event event) {
                chatArea.appendText("Pseudo : "+messageInput.getText()+"\n");

                try {
                    message = new ByteArrayInputStream(messageInput.getText().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(message);
                messageInput.clear();
            }
        });
        messageInputPane.getChildren().addAll(messageInput);

        //Connexion au serveur
        System.out.println("coucou");
        backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        try {
                            Client.connectionServer(UserService.getCurrentUser(), ChannelService.getCurrentChannel(),message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                };
            }
        };

        backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("CA MARCHE");
            }
        });

        backgroundThread.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("ca marche pas sa mere");
            }
        });

        backgroundThread.restart();
    }




    public void sendMessage(ActionEvent event) throws UnsupportedEncodingException {
        chatArea.appendText("Pseudo : "+messageInput.getText()+"\n");
        message = new ByteArrayInputStream(messageInput.getText().getBytes("UTF-8"));
        messageInput.clear();
    }

    public void switchToChannelsList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("ChannelsList.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();
    }

    /*
    public static String getMessageInput(){
        return messageInput.getText();
    }*/


}
