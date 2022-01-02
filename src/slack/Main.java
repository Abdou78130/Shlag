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

        User u=us.inscription("saren", "mdp", "saren@gmail.com", "Mastier", "Lucas");
        User u2=us.inscription("abdou78","mdp","abdou78@gmail.com","Haba","Abdallah");
        List<User> list = us.userRepository.select();
        List<Message> list_mess =ms.MessageRepository.select();

        if(us.authenticate(u.getId(),u.getPassword())) {
            Client.connectionServer(us.userRepository.select(u.getId()));
        }
    }
}
