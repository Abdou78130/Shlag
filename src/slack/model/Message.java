package slack.model;

public class Message implements HasId{
    private String message;
    private Channel channel;

    // faut une valeur ici, un message peut etre ecrit plusieurs fois donc c'est pas un id
    @Override
    public String getId() { return message; }

    public String getMessage(){
        return message;
    }
}
