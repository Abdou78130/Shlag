package slack.repository.db;

import slack.model.User;
import slack.repository.Repository;

import java.util.List;

public class UserRepository implements Repository<User> {
    @Override
    public User insert(User obj) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public User select(String id) {
        return null;
    }

    @Override
    public List<User> select() {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }
}
