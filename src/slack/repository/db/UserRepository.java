package slack.repository.db;
import java.sql.*;

import slack.model.*;
import slack.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

// User_db = Id/username/nom/prenom/mail/password/ChannelIdList

    @Override
    public User insert(User obj) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            String requete = "insert into users values (" + obj.getUserId() + ",'" + obj.getId() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getMail() + "','" + obj.getPassword() + "', ''," ;
            if (obj instanceof Admin)
                s.executeUpdate(requete+"1);");
            else
                s.executeUpdate(requete+"0);");

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();
            User user;

            //System.out.println("Connexion à la base de donnée réussie !");

            ResultSet rs = s.executeQuery("select * from users where username = '"+username+"';");
            rs.next();

            if (rs.getBoolean(8))
                user = new Admin(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            else
                user = new User(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            List<User> list = new ArrayList<User>();
            ResultSet rs = s.executeQuery("select * from users order by id;");
            while(rs.next()){
                if (rs.getBoolean(8))
                    list.add(new Admin(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                else
                    list.add(new User(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");


            String requete = "update users set username = '"+obj.getId()+"', nom = '"+obj.getNom()+"', prenom = '"+obj.getPrenom()+"', mail = '"+obj.getMail()+"', password = '"+obj.getPassword()+"'";
            if(obj instanceof Admin)
                s.executeUpdate(requete+", isAdmin = 1 where username = '"+obj.getId()+"'");
            else
                s.executeUpdate(requete+", isAdmin = 0 where username = '"+obj.getId()+"'");

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
