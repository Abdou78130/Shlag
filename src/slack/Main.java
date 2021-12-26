package slack;

import slack.model.User;
import slack.server.Server;
import slack.service.UserService;
import slack.server.Client;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService us = new UserService();

        User user1 = new User("abdou78","mdp","abdou78@gmail.com","Haba","Abdallah");
        User user2 = new User("saren","mdp","saren@gmail.com","Mastier","Lucas");
        User user3 = new User("veeko","mdp","veeko@gmail.com","Lassal","Mounir");
        User user4 = new User("miike","mdp","miike@gmail.com","Chen","Mike");

        us.userRepository.insert(user1);
        us.userRepository.insert(user2);
        us.userRepository.insert(user3);
        us.userRepository.insert(user4);

        /*
        List<User> list = us.userRepository.select();
        for(User u : list){
            System.out.println(u);
        }
        */
        System.out.println(us.authenticate("veeko","mdp"));

        Server.runServer();
    }
}
