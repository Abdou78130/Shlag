package com.example.javafxmodule;

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
import slack.service.ChannelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChannelsListController {

    @FXML
    private TilePane listChannelPane;

    private List<Button> buttonList = new ArrayList<>();

    private List<Channel> listChannel = ChannelService.getList();



    public void initialize(){
        for (Channel channel : listChannel) {
            Button button = new Button(channel.getId());
            button.setOnAction(new EventHandler() {

                                   @Override
                                   public void handle(Event event) {
                                       ChannelService.setCurrentChannel(channel);
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
