package slack;

import java.sql.*;
import slack.model.User;
import slack.server.Server;
import slack.server.Client;
import slack.service.UserService;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService us = new UserService();

        List<User> list = us.userRepository.select();

        User user1 = new User(User.getLastId(us.userRepository.select()),"abdou78","Haba","Abdallah","abdou78@gmail.com","mdp");
        /*
        User user2 = new User("saren","Mastier","Lucas","saren@gmail.com","mdp");
        User user3 = new User("veeko","Lassal","Mounir","veeko@gmail.com","mdp");
        User user4 = new User("miike","Chen","Mike","miike@gmail.com","mdp");*/
/*
       for(User user : list){
            System.out.println(user);
        }
*/
        /*us.userRepository.update(user1);
        us.userRepository.update(user2);
        us.userRepository.update(user3);
        us.userRepository.update(user4);*/

        /*List<User> list = us.userRepository.select();
        for(User u : list){
            System.out.println(u);
        }
*/
        System.out.println(us.authenticate("veeko","mdp"));

    }
}
