package slack.service;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

public class MessageService {

    public final Repository<Message> MessageRepository = RepositoryFactory.createMessage();

    public Message creerMessage(String mess, Channel channel, User user) { /**
                                                                            * Permet la création d'un message contenant
                                                                            * le user qui l'crit, le channel dans lequel
                                                                            * il l'écrit et le contenu du message
                                                                            **/
        Message message = new Message(mess, channel, user);
        MessageRepository.insert(message);
        return message;
    }

    public void supprimerMessage(String messageId) { /** Fonction qui permet la suppression d'un message **/
        Message message = MessageRepository.select(messageId);
        MessageRepository.delete(message);
    }

    public Message modifierMessage(String mess, String messageId) {
        Message message = MessageRepository.select(messageId);
        message.setMessage(mess);
        return message;
    }

}
