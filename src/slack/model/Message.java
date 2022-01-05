package slack.model;

import slack.service.*;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements HasId {
    private final int id;
    private String message;
    private final String channel;
    private final String username;
    private final String time;

    // Nouveau message
    public Message(int lastId, String message, String channel, String auteur) {
        this.message = message;
        this.channel = channel;
        this.username = auteur;
        id = lastId + 1;
        time = dateFormat(LocalDateTime.now());
    }

    // Message récupéré depuis la bdd
    public Message(int id, String message, String channel, String auteur, String time) {
        this.message = message;
        this.channel = channel;
        this.username = auteur;
        this.id = id;
        this.time = time;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    public int getIntId() {
        return id;
    }

    public String getAuteur() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public static int getLastId(List<Message> list) {
        int max = 0;
        for (Message message : list) {
            if ((message.getIntId()) > max)
                max = message.getIntId();
        }
        return max;
    }

    public static String dateFormat(LocalDateTime time) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(format);
    }

    public String toString() {
        return (username + " a écrit : " + message + " dans " + channel);
    }

    public String getUser() {
        return UserService.userRepository.select(username).getId();
    }

    public String getChannel() {
        return ChannelService.channelRepository.select(channel).getId();
    }

}
