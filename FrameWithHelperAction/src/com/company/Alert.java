package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Alert {
    private JFrame frame = new JFrame();
    private JLabel alert = new JLabel();
    private JButton closeWindow = new JButton();

    ActionListener close = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
        }
    };

    // Display an alert if user produces an error
    public void display(String message) {
        closeWindow.addActionListener(close);
        alert.setText(message);
        closeWindow.setText("ok");
        frame.add(alert);
        frame.add(closeWindow);

        frame.setSize(new Dimension(400,100));

        frame.setLayout(new GridLayout(2,1));


        frame.setTitle("Warning");
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
