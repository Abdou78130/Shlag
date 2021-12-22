package slack.repository.memory;

import slack.model.HasId;
import slack.model.User;
import slack.repository.Repository;
import slack.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryRepository<T extends HasId> implements Repository<T> {

    private final Map<String, T> data = new HashMap<>();

    public MemoryRepository(){}

    @Override
    public T insert(T obj) {
        data.put(obj.getId(), obj);
        return obj;
    }

    @Override
    public void delete(T obj) {

        data.remove(obj.getId());
    }

    @Override
    public T select(String id) {
        return data.get(id);
    }

    @Override
    public List<T> select() {
        return new ArrayList<>(data.values());
    }

    @Override
    public T update(T obj) {
        return insert(obj);
    }
}


