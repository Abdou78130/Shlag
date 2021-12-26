package slack.model;

public class User implements HasId {
    protected String username;
    protected String password;
    protected String mail;
    protected String nom;
    protected String prenom;

    public User(String username, String mdp, String mail, String nom, String prenom){
        this.username=username;
        this.password=mdp;
        this.mail=mail;
        this.nom=nom;
        this.prenom=prenom;
    }
    public void setPrenom(String p){
        this.prenom=p;
    }
    public void setNom(String n){
        this.nom=n;
    }
    public void setMail(String m){
        this.mail=m;
    }
    public void setPassword(String m){
        this.password=m;
    }
    public void setUsername(String user){
        this.username=user;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getNom(){
        return nom;
    }
    public String getMail() {
        return mail;
    }

    @Override
    public String getId() {
        return username;
    }

    public String toString(){
        return (username+" "+nom+" "+prenom+" "+mail);
    }
}
