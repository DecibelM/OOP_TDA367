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
     * Adds a new session to the Planner sessionList, without image.
     * @param sessionName name of the session
     * @param date date of the session
     */
    public void addSession(String sessionName,List<Exercise> exList,  LocalDate date){
        sessionList.add(new Session(sessionName, exList, date));
    }

    /**
     * Adds a new session to the Planner sessionList, with image.
     * @param sessionName Name of session
     * @param date Date of session
     * @param sessionImage Image of session
     */
    public void addSession(String sessionName, LocalDate date, int sessionImage){
        sessionList.add(new Session(sessionName, date, sessionImage));
    }


    public List<Session> getSessionList() {
        return sessionList;
    }

}
