package slack.model;

public class User implements HasId {
    private String pseudo;
    private String mdp;
    private String nom;
    private String prenom;
    private String mail;


    @Override
    public String getId() {
        return pseudo;
    }
}
