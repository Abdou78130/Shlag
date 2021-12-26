package slack.service;

import slack.model.Message;
import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

import java.util.function.ToDoubleBiFunction;

public class UserService {

    public final Repository<User> userRepository = RepositoryFactory.createUser();

    public boolean authenticate(String username, String password) {
        User user = userRepository.select(username);
        if (user == null) {
            System.out.println("ERREUR : Le pseudo entré n'existe pas");
            return false;
        }
        if(password==user.getPassword()){
                return true;
        }
        else{
            System.out.print("ERREUR : le mot de passe entré est incorrect");
            return false;
        }
    }


}
