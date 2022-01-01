package slack.service;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

public class MessageService {

    public final Repository<Message> MessageRepository = RepositoryFactory.createMessage();

    public Message creerMessage(String mess, Channel channel){      
        Message message = new Message(mess,channel);
        MessageRepository.insert(message);
        return message;
    }

    public void supprimerMessage(String mess){          //A changer avec l'id plutot
        Message message = MessageRepository.select(mess);
        MessageRepository.delete(message);
    }


}
