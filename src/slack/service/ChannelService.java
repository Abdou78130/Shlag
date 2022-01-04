package slack.service;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;
import java.util.List;
import java.util.ArrayList;

public class ChannelService {

    public static final Repository<Channel> channelRepository = RepositoryFactory.createChannel();
    private static Channel currentchannel = null;
    private static List<Channel> list = channelRepository.select();

    public static Channel connexionChannel(String name) {
        currentchannel = channelRepository.select(name);
        currentchannel.addUser(UserService.getCurrentUser().getUserId());
        channelRepository.update(currentchannel);
        return currentchannel;
    }

    public static void supprimerChannel(String name) {
        Channel channel = channelRepository.select(name);
        channelRepository.delete(channel);
        list.remove(channel);
    }

    public static void modifierChannel(String name) {
        if (UserService.getCurrentUser().getAdmin()) {
            Channel channel = channelRepository.select(name);
            channel.setNom(name);
            channelRepository.update(channel);
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

    public static Channel getCurrentChannel() {
        return currentchannel;
    }
}
