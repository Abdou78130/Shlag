package slack.repository.db;
import java.sql.*;

import slack.model.User;
import slack.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {
    private final String url = "jdbc:mysql://localhost:3306/shlag_db?useSSL=false";

// User_db = Id/username/nom/prenom/mail/password/ChannelIdList

    @Override
    public User insert(User obj) {
        try {
            Connection con = DriverManager.getConnection(url,"root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            s.executeUpdate("insert into users values (" + obj.getUserId() + ",'" + obj.getId() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getMail() + "','" + obj.getPassword() + "', '');");

            s.close();
            con.close();

            return obj;
        }
        catch(Exception e){
            System.out.println("Erreur lors de l'ajout à la table User !");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(User obj) {
        try {
            Connection con = DriverManager.getConnection(url,"root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            s.executeUpdate("delete from users where id = "+obj.getUserId()+";");

            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Erreur lors de la suppression de User n°"+obj.getUserId()+" de la table User !");
            e.printStackTrace();
        }
    }

    @Override
    public User select(String username) {
        try {
            Connection con = DriverManager.getConnection(url,"root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            ResultSet rs = s.executeQuery("select * from users where username = '"+username+"';");
            rs.next();
            User user = new User(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));

            s.close();
            con.close();

            return user;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la recherche dans la table User");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> select(){
        try {
            Connection con = DriverManager.getConnection(url,"root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            List<User> list = new ArrayList<User>();
            ResultSet rs = s.executeQuery("select * from users order by id;");
            while(rs.next()){
                list.add(new User(rs.getInt(1)-1,rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }

            s.close();
            con.close();

            return list;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la recherche dans la table User");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User obj) {
        try{
            Connection con = DriverManager.getConnection(url,"root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            String requete = "update users set username = '"+obj.getId()+"', nom = '"+obj.getNom()+"', prenom = '"+obj.getPrenom()+"', mail = '"+obj.getMail()+"', password = '"+obj.getPassword()+"'";
            s.executeUpdate(requete+" where username = '"+obj.getId()+"'");

            s.close();
            con.close();

            return obj;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la mise a jour de "+obj.getId());
            return null;
        }
    }
}
