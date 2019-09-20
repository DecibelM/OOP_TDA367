package com.traininapp.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Class Calendar which holds a list of sessions and
 * can add new sessions.
 */
public class Calendar {

    private List<Session> sessionList;

    public Calendar() {
        this.sessionList = new ArrayList<>();
    }

    /**
     * Adds a new session to the Calendar list.
     * @param sessionName name if the session
     * @param date date of the session
     */
    public void addSession(String sessionName, LocalDate date){
        sessionList.add(new Session(sessionName, date));
    }

    public List<Session> getSessionList() {
        return sessionList;
    }
}
