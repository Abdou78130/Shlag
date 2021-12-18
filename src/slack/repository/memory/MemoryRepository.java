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

    public static void main(String[] args) {
        UserService us = new UserService();
        User user1 = new User("abdou78","mdp","abdou78@gmail.com","Haba","Abdallah");
        User user2 = new User("saren","mdp","saren@gmail.com","Mastier","Lucas");
        User user3 = new User("veeko","mdp","veeko@gmail.com","Lassal","Mounir");
        User user4 = new User("miike","mdp","miike@gmail.com","Chen","Mike");

        us.userRepository.insert(user1);

        us.userRepository.insert(user2);
        us.userRepository.insert(user3);
        us.userRepository.insert(user4);
        List<User> list = us.userRepository.select();
        for(User u : list){
            System.out.println(u);
        }
       System.out.println(us.authenticate("veeko","mdp"));

    }
}


