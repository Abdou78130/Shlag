package slack.service;

import java.util.List;

import slack.model.*;
import slack.repository.*;
import slack.repository.db.*;

public class MessageService {

    public static final Repository<Message> messageRepository = RepositoryFactory.createMessage();
    private static List<Message> list = messageRepository.select();

    public static Message creerMessage(String mess, Channel channel, User user) {
        Message message = new Message(Message.getLastId(messageRepository.select()), mess, channel.getId(),
                user.getId());
        list.add(messageRepository.insert(message));
        messageRepository.insert(message);
        return message;
    }

    public static void supprimerMessage(String messageId) {
        Message message = messageRepository.select(messageId);
        list.remove(message);
        messageRepository.delete(message);
    }

    public static void modifierMessage(String mess, String messageId) {
        Message message = messageRepository.select(messageId);
        message.setMessage(mess);
        messageRepository.update(message);
    }

}
