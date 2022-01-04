package slack.service;

import slack.model.Channel;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

import java.util.List;

public class ChannelService {

    public static final Repository<Channel> channelRepository = RepositoryFactory.createChannel();
    private static List<Channel> list = channelRepository.select();

    public static void supprimerChannel(String name) {
        Channel channel = channelRepository.select(name);
        channelRepository.delete(channel);
        list.remove(channel);
    }

    public static void modifierChannel(String name) {

    }

    public static void creerChannel(String name) {
        Channel channel = new Channel(name, "", "");
        channelRepository.insert(channel);
        list.add(channelRepository.insert(channel));
    }

    public static List<Channel> getList(){
        return list;
    }
}
