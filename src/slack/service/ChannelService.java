package slack.service;

import slack.model.Channel;
import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;


public class ChannelService {

    public final Repository<Channel> ChannelRepository = RepositoryFactory.createChannel();

    public void supprimerChannel(String name){
        Channel channel = ChannelRepository.select(name);
        ChannelRepository.delete(channel);
    }
    
    public void modifierChannel(String name){

    }

    public void creerChannel(String name){
        Channel channel = new Channel(name);
        ChannelRepository.insert(channel);
    }
}
