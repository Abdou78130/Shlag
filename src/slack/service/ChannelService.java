package slack.service;

import slack.model.Channel;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

import java.util.List;

public class ChannelService {

    public static final Repository<Channel> channelRepository = RepositoryFactory.createChannel();
    private static List<Channel> list = channelRepository.select();
    private static Channel currentchannel = null;

    public static void supprimerChannel(String name) {
        Channel channel = channelRepository.select(name);
        channelRepository.delete(channel);
        list.remove(channel);
    }

    public static Channel connexionChannel(String name) {
        currentchannel = channelRepository.select(name);
        currentchannel.addUser(UserService.getCurrentUser().getUserId());
        channelRepository.update(currentchannel);
        return currentchannel;
    }

    public static void modifierChannel(String name) {
        if (UserService.getCurrentUser().getAdmin()) {
            currentchannel.setNom(name);
            channelRepository.update(currentchannel);
            System.out.print("Channel rennomé avec succès");
        } else {
            System.out.print("Impossible de renommé , vous n'êtes pas admin");
        }

    }

    public static void creerChannel(String name) {
        Channel channel = new Channel(name, "", "");
        channelRepository.insert(channel);
        list.add(channelRepository.insert(channel));
    }

    public static List<Channel> getList() {
        return list;
    }

    public static Channel getCurrentChannel() {
        return currentchannel;
    }
}
