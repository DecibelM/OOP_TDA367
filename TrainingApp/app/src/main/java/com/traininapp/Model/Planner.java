package com.traininapp.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Class Planner which holds a list of sessions and
 * can add new sessions.
 */
public class Planner {

    private List<Session> sessionList;
    private List<Routine> routineList;


    public Planner() {
        this.sessionList = new ArrayList<>();
        this.routineList = new ArrayList<>();

    }

    /**
     * Adds a new session to the Planner list.
     * @param sessionName name of the session
     * @param date date of the session
     */
    public void addSession(String sessionName, LocalDate date){
        sessionList.add(new Session(sessionName, getRoutineList(), date));
    }

    /**
     * Adds a new session to the Planner list, with image.
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

    public List<Routine> getRoutineList() {
        return routineList;
    }

}
