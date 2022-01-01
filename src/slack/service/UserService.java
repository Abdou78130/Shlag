package slack.service;

import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

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

    public User inscription(String username, String mdp, String mail, String nom, String prenom){
        User user= new User(User.getLastId(userRepository.select()),mdp,mail,nom,prenom);
        userRepository.insert(user);
        return user;
    }


}
