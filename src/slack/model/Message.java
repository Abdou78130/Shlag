package slack.model;

public class Message implements HasId{
    private String message;
    private Channel channel;

    @Override
    public String getId() { return message; }

}
