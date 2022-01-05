package slack.service;

import java.util.List;
import java.util.ListIterator;

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
        channel.addMessage(message.getIntId());
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

    public static String getIdLastMessage() {
        List<Integer> messages = ChannelService.getCurrentChannel().getListMessages();
        for (int i = 0; i < messages.size(); i++) {
            Message message = MessageService.messageRepository.select(String.valueOf(messages.get(i)));
            if (message.getAuteur().equals(UserService.getCurrentUser().getId())) {
                return message.getId();
            }
        }
        System.out.print("Aucun message récent de l'utilisateur sur ce channel n'a été trouvé ");
        return "";
    }
}
