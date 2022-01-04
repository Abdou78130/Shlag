package slack.model;

import slack.repository.db.*;

import java.util.ArrayList;
import java.util.List;

public class User implements HasId {
    protected final int userId;
    protected String username;
    protected String nom;
    protected String prenom;
    protected String mail;
    protected String password;
    protected boolean admin;

    public User(int lastId, String username, String nom, String prenom, String mail, String mdp) {
        this.username = username;
        this.password = mdp;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.admin = false;
        this.userId = lastId + 1;
    }

    public User(int lastId, String username, String nom, String prenom, String mail, String mdp, boolean bool) {
        this.username = username;
        this.password = mdp;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.admin = bool;
        this.userId = lastId + 1;
    }

    public void setPrenom(String p) {
        this.prenom = p;
    }

    public void setNom(String n) {
        this.nom = n;
    }

    public void setMail(String m) {
        this.mail = m;
    }

    public void setPassword(String m) {
        this.password = m;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public void setAdmin(boolean bool) {
        this.admin = bool;
    }

    public boolean getAdmin() {
        return admin;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getMail() {
        return mail;
    }

    public static int getLastId(List<User> list) {
        int max = 0;
        for (User user : list) {
            if ((user.getUserId()) > max)
                max = user.getUserId();
        }
        return max;
    }

    @Override
    public String getId() {
        return username;
    }

    public String toString() {
        return (userId + " - " + username + " " + nom + " " + prenom + " " + mail);
    }
}
