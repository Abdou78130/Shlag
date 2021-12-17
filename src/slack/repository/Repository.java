package slack.repository;

import slack.model.HasId;

import java.util.List;

public interface Repository<T extends HasId> {

    T insert(T obj);

    void delete(T obj);

    T select (String id);

    List<T> select();

    T update(T obj);

}
