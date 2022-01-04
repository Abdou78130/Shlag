package slack.repository;

import slack.model.User;
import slack.model.Channel;
import slack.model.Message;
import slack.repository.db.ChannelRepository;
import slack.repository.db.MessageRepository;
import slack.repository.db.UserRepository;
import slack.repository.memory.MemoryRepository;

public class RepositoryFactory {

    private static final boolean USE_DB = false;

    public static Repository<User> createUser() {
        if (USE_DB) {
            return new UserRepository();
        }
        return new MemoryRepository<>();
    }

    public static Repository<Channel> createChannel() {
        if (USE_DB) {
            return new ChannelRepository();
        }
        return new MemoryRepository<>();
    }

    public static Repository<Message> createMessage() {
        if (USE_DB) {
            return new MessageRepository();
        }
        return new MemoryRepository<>();
    }

    private RepositoryFactory() {
    }
}
