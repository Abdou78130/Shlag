package slack.model;

public class Message implements HasId {
    private String message;
    private Channel channel;
    private User user;
    private String messageId;

    public Message(String message, Channel channel, User user) {
        this.message = message;
        this.channel = channel;
        this.user = user;
        this.messageId = randomString();
    }

    public String randomString() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
        StringBuilder s = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int) (str.length() * Math.random());
            s.append(str.charAt(index));
        }
        return s.toString();
    }

    @Override
    public String getId() {
        return messageId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

}
