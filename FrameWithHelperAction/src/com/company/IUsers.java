package com.company;

public interface IUsers {
    public void addUsers(String name);
    public boolean inList(String nm);
    void setCurrentUsr(String usr);
    String getCurrentUsr();
}
