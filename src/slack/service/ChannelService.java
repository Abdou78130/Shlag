package slack.service;

import slack.model.User;
import slack.repository.Repository;
import slack.repository.RepositoryFactory;


public class ChannelService {

    public final Repository<Channel> ChannelRepository = RepositoryFactory.createChannel();

    public void supprimerChannel(String name){

    }
    
    public void modifierChannel(String name){

    }

    public void creerChannel(String name){

    }
}
