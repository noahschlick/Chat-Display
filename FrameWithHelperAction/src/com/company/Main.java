package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        IMessage m;
        IUsers u;

        m = new MessageController();
        u = new UserController();
        new MessageBoard(m, u);
    }





}
