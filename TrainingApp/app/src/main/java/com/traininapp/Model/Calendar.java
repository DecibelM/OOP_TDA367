package com.traininapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {

    private List<Session> sessionList;

    public Calendar() {
        this.sessionList = new ArrayList<>();
    }


    public void addSession(String sessionName){
        sessionList.add(new Session(sessionName));
    }

    public List<Session> getSessionList() {
        return sessionList;
    }
}
