package slack.repository;

import slack.model.User;
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

    private RepositoryFactory() {}
}
