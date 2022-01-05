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
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import slack.model.Channel;
import slack.server.Client;
import slack.service.ChannelService;
import slack.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChannelsListController {

    @FXML
    private TilePane listChannelPane;

    private List<Button> buttonList = new ArrayList<>();

    private List<Channel> listChannel = ChannelService.getList();

    private Service<Void> backgroundThread;

    public void addChannelToList(ActionEvent event){
        for (Channel channel : listChannel) {
            Button button = new Button(channel.getId());
            button.setOnAction(new EventHandler() {

                                   @Override
                                   public void handle(Event event) {
                                       FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("ChannelChat.fxml"));

                                       //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                       Scene scene = null;
                                       try {
                                           scene = new Scene(fxmlLoader.load(),1920,1080);
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }

                                       stage.setScene(scene);
                                       stage.show();


                                       //Connexion au serveur
                                       backgroundThread = new Service<Void>() {
                                           @Override
                                           protected Task<Void> createTask() {
                                               return new Task<Void>() {
                                                   @Override
                                                   protected Void call() throws Exception {

                                                       try {
                                                           Client.connectionServer(UserService.getCurrentUser(),channel,ChannelChatController.message);
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

                               });
            buttonList.add(button);

        }
        listChannelPane.getChildren().clear();
        listChannelPane.getChildren().addAll(buttonList);
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("Menu.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();
    }


}
