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
     * @param sessionName name if the session
     * @param date date of the session
     */
    public void addSession(String sessionName, LocalDate date){
        sessionList.add(new Session(sessionName, getRoutineList(), date));
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public List<Routine> getRoutineList() {
        return routineList;
    }

}
