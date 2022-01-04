package slack;

import slack.model.Message;
import slack.model.User;
import slack.server.Client;
import slack.service.ChannelService;
import slack.service.MessageService;
import slack.service.UserService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        User u = UserService.inscription("saren", "mdp", "saren@gmail.com", "Mastier", "Lucas");
        User u2 = UserService.inscription("abdou78", "mdp", "abdou78@gmail.com", "Haba", "Abdallah");
        User u3 = UserService.inscription("veeko","mdp","mounir@gmail.com","Lassal","Mounir");
        User u4 = UserService.inscription("miike","mdp","mike@gmail.com","Chen","Mike",true);
        ChannelService.creerChannel("#general");
        ChannelService.creerChannel("#ProjetShlag");
        ChannelService.creerChannel("#Cours");
        List<User> list = UserService.userRepository.select();
        List<Message> list_mess = MessageService.messageRepository.select();

        if (UserService.authenticate(u4.getId(), u4.getPassword()) != null) {
            Client.connectionServer(UserService.userRepository.select(u4.getId()));
        }
    }
}