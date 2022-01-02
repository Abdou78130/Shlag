package slack.service;

import slack.model.*;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;

import java.util.ArrayList;

public class ChannelService {

    public final Repository<Channel> channelRepository = RepositoryFactory.createChannel();

    public void supprimerChannel(String name) {
        Channel channel = channelRepository.select(name);
        channelRepository.delete(channel);
    }

    public void modifierChannel(String name) {

    }

    public void creerChannel(String name) {
        Channel channel = new Channel(name, "", "");
        channelRepository.insert(channel);
    }
}
