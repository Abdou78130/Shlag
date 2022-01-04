package slack.service;

import java.util.List;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;
import slack.repository.db.UserRepository;

public class MessageService {

    public static final Repository<Message> messageRepository = RepositoryFactory.createMessage();
    private static List<Message> list = messageRepository.select();

    public static Message creerMessage(String mess, Channel channel, User user) { /**
                                                                                   * Permet la création d'un message
                                                                                   * contenant
                                                                                   * le user qui l'crit, le channel dans
                                                                                   * lequel
                                                                                   * il l'écrit et le contenu du message
                                                                                   **/
        Message message = new Message(Message.getLastId(messageRepository.select()),mess, channel.getId(), user.getId());
        list.add(messageRepository.insert(message));
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
        messageRepository.insert(message);
        message.setMessage(mess);
        return message;
    }

}
