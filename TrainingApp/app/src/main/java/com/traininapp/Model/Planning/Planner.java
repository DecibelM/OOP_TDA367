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
    private List<Routine> routineList;
    private List<Exercise> exerciseList;

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

    public void addSession(String sessionName, LocalDate date, List<Exercise> list){
        sessionList.add(new Session(sessionName, date, list));
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public List<Routine> getRoutineList() {
        return routineList;
    }

    /**
     * Method used for printing details of the Sessions in Planner's list. Used only for
     * making sure Sessions were added correctly.
     */
    public void printSessionDetails(){

        int i = 1;

        for (Session session : sessionList){

            System.out.println("");
            System.out.print(i + ". ");
            System.out.println("Name of session: " + session.getName());
            System.out.println("Date of session: " + session.getDate().toString());

            if (session.getExerciseList().isEmpty()) {
                System.out.println("There are no exercises in session " + session.getName());
            } else {
                System.out.println("Exercises in session: ");
                for (Exercise exercise : session.getExerciseList()){
                    System.out.println("   " + exercise.getName());
                }
            }
            i++;
        }
    }

}
