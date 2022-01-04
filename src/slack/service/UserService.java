package slack.service;

import slack.model.*;
import slack.repository.*;

import java.util.List;

public class UserService {

    public static final Repository<User> userRepository = RepositoryFactory.createUser();
    private static List<User> list = userRepository.select();
    private static User currentuser = null;

    public static User authenticate(String username, String password) { /**
                                                                         * Fonction permettant a un utilisateur de se
                                                                         * connecter en rentrant un username et le
                                                                         * mot de
                                                                         * passe associé
                                                                         **/
        User user = userRepository.select(username);
        if (user == null) {
            System.out.println("ERREUR : Le pseudo entré n'existe pas");
            return null;
        }
        if (password.equals(user.getPassword())) {
            currentuser = user;
            return user;
        } else {
            System.out.print("ERREUR : le mot de passe entré est incorrect");
            return null;
        }
    }

    public static User inscription(String username, String mdp, String mail, String nom, String prenom) {
        User user = new User(User.getLastId(userRepository.select()), username, nom, prenom, mail, mdp);
        userRepository.insert(user);
        list.add(user);
        return user;
    }

    public static User inscription(String username, String mdp, String mail, String nom, String prenom, boolean admin) {
        User user = new User(User.getLastId(userRepository.select()), username, nom, prenom, mail, mdp, admin);
        userRepository.insert(user);
        list.add(user);
        return user;
    }

    public static User getCurrentUser() {
        return currentuser;
    }

    public static List<User> getList() {
        return list;
    }

}
