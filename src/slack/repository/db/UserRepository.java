package slack.repository.db;
import java.sql.*;

import slack.model.*;
import slack.repository.*;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

// User_db = Id/username/nom/prenom/mail/password/ChannelIdList/isAdmin

    @Override
    public User insert(User obj) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            String requete = "insert into users values (" + obj.getUserId() + ",'" + obj.getId() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getMail() + "','" + obj.getPassword() + "', ''," ;
            if (obj.getAdmin())
                s.executeUpdate(requete+"1);");
            else
                s.executeUpdate(requete+"0);");

            s.close();
            con.close();

            return obj;
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Cette id existe deja !");
            return null;
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

            if(s.executeUpdate("delete from users where id = "+obj.getUserId()+";")==0)
                System.out.println("delete : Ce user n'existe pas !");

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
            User user = null;

            //System.out.println("Connexion à la base de donnée réussie !");

            ResultSet rs = s.executeQuery("select * from users where username = '"+username+"';");
            if(rs.next()) {

                if (rs.getBoolean(8))
                    user = new User(rs.getInt(1) - 1, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),true);
                else
                    user = new User(rs.getInt(1) - 1, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),false);
            }
            else
                System.out.println("select : "+username+" n'existe pas !");
            
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
            if(rs.next()){
                do{
                    if (rs.getBoolean(8))
                        list.add(new User(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),true));
                    else
                        list.add(new User(rs.getInt(1)-1,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),false));
                }while(rs.next());
            }
            else
                System.out.println("select : Il n'y a aucun User !");

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
            int res;

            //System.out.println("Connexion à la base de donnée réussie !");

            String requete = "update users set username = '"+obj.getId()+"', nom = '"+obj.getNom()+"', prenom = '"+obj.getPrenom()+"', mail = '"+obj.getMail()+"', password = '"+obj.getPassword()+"'";

            if(obj.getAdmin())
                res = s.executeUpdate(requete+", isAdmin = 1 where username = '"+obj.getId()+"'");
            else
                res = s.executeUpdate(requete+", isAdmin = 0 where username = '"+obj.getId()+"'");

            if(res==0){
                System.out.println("update : "+obj.getId()+" n'existe pas !");
            }

            s.close();
            con.close();

            return obj;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la mise a jour de "+obj.getId());
            e.printStackTrace();
            return null;
        }
    }
}
