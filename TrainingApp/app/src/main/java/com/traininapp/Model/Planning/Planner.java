package com.traininapp.Model.Planning;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Planner which holds a list of sessions and
 * can add new sessions.
 * @author Maria Fornmark
 */
public class Planner {

    private List<Session> sessionList;

    /**
     * Constructor for planner
     */
    public Planner() {
        this.sessionList = new ArrayList<>();
    }

    /**
     * Adds a new session to the Planner sessionList.
     * @param session Name of session
     */
    public void addSession(Session session){
        sessionList.add(session);
    }

    public List<Session> getSessionList() {
        return sessionList;
    }
}
