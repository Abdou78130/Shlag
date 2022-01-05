package slack;

import java.sql.*;
import slack.model.*;
import slack.server.*;
import slack.service.*;
import slack.repository.*;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class Mainabd {
    public static void main(String[] args) throws IOException {
        /*
         * UserService us = new UserService();
         * 
         * List<User> list = us.userRepository.select();
         * 
         * 
         * User user1 = new
         * User(0,"abdou78","Haba","Abdallah","abdou78@gmail.com","mdp");
         * User user2 = new User(1,"saren","Mastier","Lucas","saren@gmail.com","mdp");
         * User user3 = new User(2,"veeko","Lassal","Mounir","veeko@gmail.com","mdp");
         * User user4 = new User(3,"miike","Chen","Mike","miike@gmail.com","mdp");
         * 
         * us.userRepository.insert(user1);
         * us.userRepository.insert(user2);
         * us.userRepository.insert(user3);
         * us.userRepository.insert(user4);
         * 
         * ChannelService cs = new ChannelService();
         * 
         * List<Channel> list = cs.channelRepository.select();
         * 
         * Channel chan1 = new Channel("chan1","2,7","5");
         * Channel chan2 = new Channel("chan2","","");
         * Channel chan3 = new Channel("chan3","","");
         * 
         * 
         * 
         * cs.channelRepository.update(chan1);
         * /*cs.channelRepository.insert(chan2);
         * cs.channelRepository.insert(chan3);
         */

        Message mess1 = new Message(0, "Hello world !", "#general", "veeko");
        Message mess2 = new Message(1, "I sleep !", "#general", "saren");
        Message mess3 = new Message(2, "We''re dead !", "#project", "miike");
        Message mess4 = new Message(3, "pfff...", "#general", "abdou78");

        // ms.MessageRepository.delete(mess1);
        // ms.MessageRepository.delete(mess1);
        // ms.MessageRepository.update(mess1);
        //MessageService.messageRepository.insert(mess1);

    }
}
