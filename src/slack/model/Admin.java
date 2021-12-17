package slack.model;

import slack.model.User;

public class Admin extends User {
    public Admin(String username, String mdp, String mail, String nom, String prenom){
        super(username,mdp, mail, nom, prenom);
    }
}
