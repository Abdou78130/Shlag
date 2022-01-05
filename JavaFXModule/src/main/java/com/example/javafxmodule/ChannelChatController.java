package com.example.javafxmodule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ChannelChatController {

    @FXML
    private TextArea chatArea;

    @FXML
    private static TextField messageInput;

    private static ByteArrayInputStream message;

    static {
        try {
            message = new ByteArrayInputStream(messageInput.getText().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ActionEvent event) throws UnsupportedEncodingException {
        chatArea.appendText("Pseudo : "+messageInput.getText()+"\n");
        message = new ByteArrayInputStream(messageInput.getText().getBytes("UTF-8");
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

    public static String getMessageInput(){
        return messageInput.getText();
    }


}
