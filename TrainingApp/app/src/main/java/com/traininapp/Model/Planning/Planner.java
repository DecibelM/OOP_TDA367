package com.traininapp.Model.Planning;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Class Planner which holds a list of sessions and
 * can add new sessions.
 */
public class Planner {

    private List<Session> sessionList;

    public Planner() {
        this.sessionList = new ArrayList<>();
    }

    /**
     * Adds a new session to the Planner sessionList, with image.
     * @param session Name of session
     */
    public void addSession(Session session){
        sessionList.add(session);
    }

    /**
     * Adds a new session to the Planner sessionList, without image.
     * @param sessionName name of the session
     * @param date date of the session
     * @param list list of exercises
     */
    public void addSession(String sessionName, LocalDate date, List<Exercise> list){
        sessionList.add(new Session(sessionName, list, date));
    }

    /**
     * Adds a new Session to Planner sessionList, with name, date, list and image
     * @param sessionName Name of Session
     * @param date Date of Session
     * @param list Exercise list of Session
     * @param sessionImage Image of Session
     */
    public void addSession(String sessionName, LocalDate date, List<Exercise> list, int sessionImage){
        sessionList.add(new Session(sessionName, date, list, sessionImage));
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

}
