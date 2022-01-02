package slack.repository.db;

import slack.model.Channel;
import slack.model.Message;
import slack.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements Repository<Message> {

    //message_db = id/message/auteur/channel/time

    @Override
    public Message insert(Message obj) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            String requete = "insert into messages values ('" + obj.getIntId() + "','" + obj.getMessage() + "','" + obj.getAuteur() + "','" + obj.getChannel() + "', '"+obj.getTime()+"');";
            s.executeUpdate(requete);


            s.close();
            con.close();

            return obj;
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Cette id existe deja !");
            return null;
        }catch(Exception e){
            System.out.println("Erreur lors de l'ajout à la table Messages !");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Message obj) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            if(s.executeUpdate("delete from messages where id = "+obj.getIntId()+";")==0)
                System.out.println("delete : Ce message n'existe pas !");

            s.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Erreur lors de la suppression de User n°"+obj.getIntId()+" de la table Message !");
            e.printStackTrace();
        }
    }

    @Override
    public Message select(String id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();
            Message message = null;

            //System.out.println("Connexion à la base de donnée réussie !");

            ResultSet rs = s.executeQuery("select * from messages where id = "+Integer.parseInt(id)+";");
            if(rs.next())
                message = new Message(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getString(5));
            else
                System.out.println("select : Ce message n'existe pas !");

            s.close();
            con.close();

            return message;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la recherche dans la table Message");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> select() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");

            List<Message> list = new ArrayList<Message>();
            ResultSet rs = s.executeQuery("select * from messages order by id;");

            if(rs.next()) {
                do {
                    list.add(new Message(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3),rs.getString(5)));
                }
                while (rs.next());
            }
            else{
                System.out.println("Il y a aucun message dans la base de données");
            }

            s.close();
            con.close();

            return list;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la recherche dans la table Message");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Message update(Message obj) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shlag_db?useSSL=false","root","1234");
            Statement s = con.createStatement();

            //System.out.println("Connexion à la base de donnée réussie !");


            int res = s.executeUpdate( "update messages set id = "+obj.getIntId()+", message = '"+obj.getMessage()+"', auteur = '"+obj.getAuteur()+"', channel = '"+obj.getChannel()+"' where id = '"+obj.getIntId()+"';");

            if(res==0) {
                System.out.println("update : Ce message n'existe pas !");
            }

            s.close();
            con.close();

            return obj;
        }
        catch(Exception e){
            System.out.println("Erreur lors de la mise a jour du message n°"+obj.getIntId());
            e.printStackTrace();
            return null;
        }
    }

}
