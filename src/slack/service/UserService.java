package slack.service;

import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

public class UserService {

    private final Repository<User> userRepository = RepositoryFactory.createUser();

    public boolean authenticate(String name, String password) {
        User user = userRepository.select(name);
        if (user == null) {
            System.out.print("ERREUR : Le pseudo rentré n'existe pas");
            return false;
        }
        if(password==user.getPassword()){
                return true;
        }
        else{
            System.out.print("ERREUR : le mot de passe rentré est incorrect");
            return false;
        }
    }


}
