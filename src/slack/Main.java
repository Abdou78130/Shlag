package slack;

import java.sql.*;
import slack.model.*;
import slack.server.*;
import slack.service.*;
import slack.repository.*;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService us = new UserService();
        MessageService ms = new MessageService();

        User u = UserService.inscription("saren", "mdp", "saren@gmail.com", "Mastier", "Lucas");
        User u2 = UserService.inscription("abdou78", "mdp", "abdou78@gmail.com", "Haba", "Abdallah");
        List<User> list = UserService.userRepository.select();
        List<Message> list_mess = MessageService.messageRepository.select();

        if (UserService.authenticate(u.getId(), u.getPassword())) {
            Client.connectionServer(UserService.userRepository.select(u.getId()));
        }
    }
}
