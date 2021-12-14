package com.company;

import java.util.LinkedList;

public class UserController implements IUsers  {
    private LinkedList<String> _userList = new LinkedList<String>();
    private String currentUsr = new String();

    /********************* addUsers() ************************
     Adds a user to the users list
     *********************************************************/
    public void addUsers(String name) {
        _userList.add(name);
    }


    /********************* inList() ************************
     Checks if a users name is already in the user list. Returns
     true if there is a user in the list.
     *********************************************************/
    public boolean inList(String nm) {
        System.out.println(nm);
        for(String item : _userList) {
            System.out.println(item);
            if (item.equals(nm)) {
                return true;
            }
        }
        System.out.println("f");
        return false;
    }

    /****************** setCurrentUsr() *********************
     Sets the current user that is displaying messages.
     *********************************************************/
    public void setCurrentUsr(String usr) {
        currentUsr = usr;
    }

    /****************** setCurrentUsr() *********************
     Sets the current user that is displaying messages.
     *********************************************************/
    public String getCurrentUsr() {
        return currentUsr;
    }


}
