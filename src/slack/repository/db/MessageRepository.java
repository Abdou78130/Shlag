package slack.repository.db;

import slack.model.Message;
import slack.repository.Repository;

import java.util.List;

public class MessageRepository implements Repository<Message> {
    @Override
    public Message insert(Message obj) {
        return null;
    }

    @Override
    public void delete(Message obj) {

    }

    @Override
    public Message select(String id) {
        return null;
    }

    @Override
    public List<Message> select() {
        return null;
    }

    @Override
    public Message update(Message obj) {
        return null;
    }
}
