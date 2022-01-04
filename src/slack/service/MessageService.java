package slack.service;

import java.util.List;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;
import slack.repository.db.UserRepository;

import java.util.ArrayList;

public class MessageService {

    public static final Repository<Message> messageRepository = RepositoryFactory.createMessage();
    private static List<Message> list = messageRepository.select();

    public Message creerMessage(String mess, String channel, String username) {
        Message message = new Message(Message.getLastId(new ArrayList<Message>()), mess, channel, username);
        messageRepository.insert(message);
        return message;
    }

    public static void supprimerMessage(String messageId) { /** Fonction qui permet la suppression d'un message **/
        Message message = messageRepository.select(messageId);
        list.remove(message);
        messageRepository.delete(message);
    }

    public static Message modifierMessage(String mess, String messageId) {
        Message message = messageRepository.select(messageId);
        message.setMessage(mess);
        return message;
    }

}
