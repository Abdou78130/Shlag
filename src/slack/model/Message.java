package slack.model;

public class Message implements HasId{
    private String message;
    private Channel salon;
    private User name;
    private static int cpt=0;
    private String messageId;

    public Message( String m, Channel c, User u){
        this.message=m;
        this.salon=c;
        this.name=u;
        messageId=String.valueOf(cpt);
        cpt++;
    }
    public String getMessage(){
        return this.message;
    }
    public Channel getChannel(){
        return this.salon;
    }
    public User getUser(){
        return this.name;
    }
    public String toString(){
        return name+": "+message;
    }
    @Override
    public String getId() { return messageId+toString(); }

}
