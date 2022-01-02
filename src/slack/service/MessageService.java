package slack.service;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

import java.util.ArrayList;

public class MessageService {

    public final Repository<Message> MessageRepository = RepositoryFactory.createMessage();

    public Message creerMessage(String mess, String channel,String username){
        Message message = new Message(Message.getLastId(new ArrayList<Message>()),mess,channel,username);
        MessageRepository.insert(message);
        return message;
    }

    public void supprimerMessage(String mess){          //A changer avec l'id plutot
        Message message = MessageRepository.select(mess);
        MessageRepository.delete(message);
    }


}
