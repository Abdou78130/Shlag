package slack.repository.db;

import slack.model.Admin;
import slack.model.Channel;
import slack.model.User;
import slack.repository.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChannelRepository implements Repository<Channel> {

    //channel_db = nom/id_users/id_messages

    @Override
    public Channel insert(Channel obj) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            String requete = "insert into channels values ('" + obj.getId() + "','" + obj.getUsers() + "','" + obj.getMessages() + "');";
            s.executeUpdate(requete);

            s.close();
            con.close();

            return obj;
        }
        catch(Exception e){
            System.out.println("Erreur lors de l'ajout à la table Channel !");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Channel obj) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            s.executeUpdate("delete from channels where nom = '"+obj.getId()+"';");

            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Erreur lors de la suppression de User n°"+obj.getId()+" de la table Channel !");
            e.printStackTrace();
        }
    }

    @Override
    public Channel select(String id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();
            Channel channel = null;
            String users = "";
            String messages = "";

            //System.out.println("Connexion à la base de donnée réussie !");

            ResultSet rs = s.executeQuery("select * from channels where nom = '"+id+"';");
            if(rs.next())
                if(rs.getString(2) == ""){
                    users = ",";
                }
                else{
                    users = rs.getString(2);
                }
                if(rs.getString(3) == ""){
                    messages = ",";
                }
                else{
                    messages = rs.getString(3);
                }
                channel = new Channel(id,users,messages);

            s.close();
            con.close();

            return channel;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la recherche dans la table Channel");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Channel> select() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            List<Channel> list = new ArrayList<Channel>();
            ResultSet rs = s.executeQuery("select * from channels order by nom;");
            String users = "";
            String messages = "";

            while(rs.next()){
                if(rs.getString(2) == ""){
                    users = ",";
                }
                else{
                    users = rs.getString(2);
                }
                if(rs.getString(3) == ""){
                    messages = ",";
                }
                else{
                    messages = rs.getString(3);
                }
                list.add(new Channel(rs.getString(1),users,messages));
            }

            s.close();
            con.close();

            return list;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la recherche dans la table Channel");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Channel update(Channel obj) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");


            s.executeUpdate( "update channels set nom = '"+obj.getId()+"', users = '"+obj.getUsers()+"', messages = '"+obj.getMessages()+"' where nom = '"+obj.getId()+"';");

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
