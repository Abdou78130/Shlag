package slack.model;

public class User implements HasId {

    private String name;

    @Override
    public String getId() {
        return name;
    }
}
