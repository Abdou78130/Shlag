package slack.service;

import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

public class UserService {

    public final Repository<User> userRepository = RepositoryFactory.createUser();

    public boolean authenticate(String username, String password) { /**
                                                                     * Fonction permettant a un utilisateur de se
                                                                     * connecter en rentrant un username et le mot de
                                                                     * passe associé
                                                                     **/
        User user = userRepository.select(username);
        if (user == null) {
            System.out.println("ERREUR : Le pseudo entré n'existe pas");
            return false;
        }
        if (password == user.getPassword()) {
            return true;
        } else {
            System.out.print("ERREUR : le mot de passe entré est incorrect");
            return false;
        }
    }

    public User inscription(String username, String mdp, String mail, String nom, String prenom) { /**
                                                                                                    * Fonction
                                                                                                    * permettant
                                                                                                    * l'inscription d'un
                                                                                                    * nouveau
                                                                                                    * utilisateur et son
                                                                                                    * ajout a la base de
                                                                                                    * donnée
                                                                                                    **/
        User user = new User(User.getLastId(userRepository.select()), username, nom, prenom, mail, mdp);
        userRepository.insert(user);
        return user;
    }

}
