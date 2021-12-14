package com.company;

import java.util.LinkedList;
import java.util.Stack;

public class MessageController implements IMessage {
    LinkedList<Message> _msgLst = new LinkedList<Message>();

    /********************* post() ************************
     Adds a message object to the message list. Each message
     object contains the message that is being sent, and
     the name of the user sending the message.
     *********************************************************/
    public LinkedList post(String msgText, String user) {
        Message msg = new Message();
        msg.message = msgText;
        msg.user = user;
        _msgLst.add(msg);
        return _msgLst;
    }

    /********************* getMessages() ************************
     returns the message linked list
     *********************************************************/
    public LinkedList getMessages() {
        return _msgLst;
    }

}
