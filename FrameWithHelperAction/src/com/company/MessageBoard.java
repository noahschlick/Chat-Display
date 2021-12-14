package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MessageBoard {

    private Alert alert = new Alert();

    private Font f = new Font("Sans Serif",Font.BOLD, 24);

    final int FIELD_WIDTH = 20;

    private JTextField textField = new JTextField(FIELD_WIDTH);
    private JTextField userField = new JTextField(FIELD_WIDTH);

    private JPanel pnl = new JPanel();
    private JPanel msgPnl = new JPanel();
    private JPanel usrPnl = new JPanel();
    private JPanel blPnl = new JPanel();
    private JPanel brPnl = new JPanel();

    private JButton sendBtn = new JButton("Send");
    private JButton addUsrBtn = new JButton("Add User");

    JFrame frame = new JFrame();

    private JScrollPane scrollPane = new JScrollPane(
            msgPnl,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );

    private IMessage _msgControl;
    private IUsers _usrControl;

    /****************** Display Message *********************
    This object displays the message on the message panel. It
    takes in the message object, and the name of the current user
    that is sending the message at that time.
     *****************************************************/
    private void displayMessage (Message m, String crntUsr) {
        // Create new message display
        JPanel listContainer = new JPanel();
        JLabel label = new JLabel("Hello");
        JLabel name = new JLabel(m.user);
        JPanel panel = new JPanel();

        label.setText(m.message);
        label.setForeground(Color.GREEN);
        name.setForeground(Color.lightGray);

        panel.add(label);
        panel.setBackground(Color.darkGray);
        listContainer.setBackground(Color.darkGray);

        // Set the position of the message
        if (m.user == crntUsr) {
            listContainer.add(panel);
            listContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        } else {
            listContainer.add(name);
            listContainer.add(panel);
            listContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        }

        // Add Message to message panel
        msgPnl.add(listContainer);
        msgPnl.setBackground(Color.darkGray);
        msgPnl.setLayout(new BoxLayout(msgPnl, BoxLayout.Y_AXIS));
    }

    /****************** post Action Listener *********************
     The action post a message on a message board.
     *****************************************************/
    ActionListener post = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedList <Message> msgList = _msgControl.post(
                    textField.getText(),
                    _usrControl.getCurrentUsr()
            );

            if (textField.getText().isEmpty()) {
                alert.display("ERROR: Message field empty");
            } else {
                displayMessage(msgList.peekLast(), _usrControl.getCurrentUsr());
                displayMessageBoard();
                textField.setText("");
            }


        }
    };

    /************ displayUser Action Listener **************
     Adds a new user to the user list. Displays a new user
     on the user panel.
     *****************************************************/
    ActionListener displayUser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (userField.getText().isEmpty()) {
                alert.display("ERROR: Empty text field!");
            } else if (_usrControl.inList(userField.getText())){
                alert.display("ERROR: Duplicate user name!");
            } else {
                _usrControl.addUsers(userField.getText());
                displayUsers(userField.getText());
                displayMessageBoard();
            }

            userField.setText("");
        }
    };

    /************ displayMessages Action Listener **************
     This action is called when a certain user is selected.
     The Message panel will display the messages in that users'
     perspective.
     *********************************************************/
    ActionListener displayMessages = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            msgPnl.removeAll();
            msgPnl.revalidate();
            msgPnl.repaint();

            _usrControl.setCurrentUsr(e.getActionCommand());
            LinkedList<Message> _msgLst = _msgControl.getMessages();

            for (  Message item : _msgLst ) {
                displayMessage (item, _usrControl.getCurrentUsr());
            }

        }
    };


    /******************** MessageBoard() *********************
     Constructor builds the start state of the message board
     *********************************************************/
    public MessageBoard(IMessage msgControl, IUsers usrControl) {
        _msgControl = msgControl;
        _usrControl = usrControl;

        sendBtn.addActionListener(post);
        addUsrBtn.addActionListener(displayUser);

        _usrControl.addUsers("Me");
        _usrControl.setCurrentUsr("Me");
        displayUsers("Me");

        msgPnl.setBackground(Color.darkGray);

        displayMessageBoard();
    }

    /***************** displayMessageBoard() ****************
     Displays the message board
     *********************************************************/
    private void displayMessageBoard() {
        msgPnl.setBackground(Color.darkGray);

        blPnl.add(userField);
        blPnl.add(addUsrBtn);

        blPnl.setLayout(new GridLayout(2,0));

       blPnl.setPreferredSize(new Dimension(200, 100));
       //blPnl.setBackground(Color.darkGray);
        brPnl.add(textField);
        brPnl.add(sendBtn);

        pnl.setLayout(new BorderLayout());
        pnl.add( blPnl, BorderLayout.WEST);
        pnl.add(brPnl, BorderLayout.EAST);

        usrPnl.setPreferredSize(new Dimension(200, 550));
        usrPnl.setLayout(new GridLayout(10, 2));
        usrPnl.setBackground(Color.darkGray);
        frame.add(pnl, BorderLayout.PAGE_END);
        frame.add(usrPnl, BorderLayout.WEST);


        //frame.add(memberBtn, BorderLayout.PAGE_START);
        scrollPane.setPreferredSize(new Dimension(600, 500));
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CHATTY");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /******************** displayUsers() ********************
     Displays the new selection for a user when a user is
     added to the group chat.
     *********************************************************/
    private void displayUsers(String userName) {
        JButton user = new JButton();
        user.addActionListener(displayMessages);
        user.setText(userName);
        usrPnl.add(user);
    }


}
