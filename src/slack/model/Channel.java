package slack.model;

import slack.repository.db.MessageRepository;
import slack.service.*;

public class Channel implements HasId{
    private String name;
    private MessageService messages;

    public Channel(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String getId() {
        return name;
    }
}
