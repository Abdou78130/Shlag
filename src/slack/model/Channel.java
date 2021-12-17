package slack.model;

import slack.repository.db.MessageRepository;

public class Channel implements HasId{
    private String name;
    private MessageRepository messages;

    @Override
    public String getId() {
        return name;
    }
}
