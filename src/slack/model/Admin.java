package slack.model;

import slack.model.User;

public class Admin extends User {
    public Admin(int lastId, String username, String mdp, String mail, String nom, String prenom){
        super(lastId, username, mdp, mail, nom, prenom);
    }
}
