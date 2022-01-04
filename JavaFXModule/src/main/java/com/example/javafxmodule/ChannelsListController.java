package com.example.javafxmodule;

import javafx.event.ActionEvent;
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

    List<Button> buttonList = new ArrayList<>();

    List<Channel> listChannel = ChannelService.getList();

    public void addChannelToList(ActionEvent event){
        System.out.println("entrée dans la fonction");
        for (Channel channel : listChannel) {
            System.out.println(channel.getId());
            buttonList.add(new Button(channel.getId()));
            System.out.println(channel.getId());
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
