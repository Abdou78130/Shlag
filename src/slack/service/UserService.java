package slack.service;

import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

public class UserService {

    private final Repository<User> userRepository = RepositoryFactory.createUser();

    public void authenticate(String name, String password) {
        User user = userRepository.select(name);
        if (user == null) {
            //TODO : Erreur user existe pas
            System.out.println("ERREUR : User n'existe pas");
        }
        else{
            if(password==user.getMdp()){
                
            }
        }
        

    }

}
