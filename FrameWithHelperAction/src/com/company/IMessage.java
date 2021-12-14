package com.company;
import java.util.LinkedList;

public interface IMessage {
    public LinkedList post(String msgText, String user);
    public LinkedList getMessages();
}
