package slack.model;

import slack.repository.db.*;
import slack.service.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Channel implements HasId {
    private String name;
    private List<Integer> messages = new ArrayList<Integer>();
    private List<Integer> users = new ArrayList<Integer>();

    public Channel(String name, String listUsers, String listMessages) {
        this.name = name;
        if (listMessages == "")
            for (String id : spr(listMessages.split(","))) {

                messages.add(Integer.parseInt(id));
            }
        if (listUsers == "") {
            listUsers = ",";
        }
        for (String id : spr(listUsers.split(","))) {
            messages.add(Integer.parseInt(id));
        }
    }

    public static List<String> spr(String[] list) {
        List<String> res = new ArrayList<String>();
        Collections.addAll(res, list);
        while (res.remove(""))
            ;
        return res;

    }

    @Override
    public String getId() {
        return name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public List<Integer> getListMessages() {
        return messages;
    }

    public String getMessages() {
        String s = "";
        for (int n : messages) {
            s += n + ",";
        }
        return s;
    }

    public String getUsers() {
        String s = "";
        for (int n : users) {
            s += n + ",";
        }
        return s;
    }

    public void addMessage(int messageId) {
        messages.add(messageId);
    }

    public void addUser(int userId) {
        users.add(userId);
    }

    public String toString() {
        return ("Channel : " + getId() + ", nb participants : " + users.size() + ", nb msg : " + messages.size());
    }
}
