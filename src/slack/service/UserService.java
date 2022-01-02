package slack.service;

import java.util.List;

import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

public class UserService {

    public static final Repository<User> userRepository = RepositoryFactory.createUser();
    private static List<User> list = userRepository.select();
    private static User currentuser = null;

    public static boolean authenticate(String username, String password) { /**
                                                                            * Fonction permettant a un utilisateur de se
                                                                            * connecter en rentrant un username et le
                                                                            * mot de
                                                                            * passe associé
                                                                            **/
        User user = userRepository.select(username);
        if (user == null) {
            System.out.println("ERREUR : Le pseudo entré n'existe pas");
            return false;
        }
        if (password.equals(user.getPassword())) {
            currentuser = user;
            return true;
        } else {
            System.out.print("ERREUR : le mot de passe entré est incorrect");
            return false;
        }
    }

    public static User inscription(String username, String mdp, String mail, String nom, String prenom) { /**
                                                                                                           * Fonction
                                                                                                           * permettant
                                                                                                           * l'inscription
                                                                                                           * d'un
                                                                                                           * nouveau
                                                                                                           * utilisateur
                                                                                                           * et son
                                                                                                           * ajout a la
                                                                                                           * base de
                                                                                                           * donnée
                                                                                                           **/
        User user = new User(User.getLastId(userRepository.select()), username, nom, prenom, mail, mdp);
        list.add(userRepository.insert(user));
        return user;
    }

    public User getCurrentUser() {
        return currentuser;
    }

    public List<User> getList() {
        return list;
    }

}
