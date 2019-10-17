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

    // TODO Vill vi ha en lista med exercises?
    private List<Exercise> exerciseList;

    public Planner() {
        this.sessionList = new ArrayList<>();
        this.routineList = new ArrayList<>();
        this.exerciseList = new ArrayList<>();
    }

    public void addSession(String sessionName, LocalDate date, List<Exercise> exerciseList){

        Session session = new Session(sessionName, date, exerciseList);

        sessionList.add(session);
    }

    /**
     * Adds a new session to the Planner sessionList, without image.
     * @param sessionName name of the session
     * @param date date of the session
     */
    public void addSession(String sessionName, LocalDate date){
        sessionList.add(new Session(sessionName, getRoutineList(), date));
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

    /**
     * Method to add a Routine to the Planner's routineList
     * @param routine Name of Routine
     */
    public void addRoutine(Routine routine){
        routineList.add(routine);
    }

    /**
     * Method to add an Exercise to the Planner's exerciseList
     * @param exercise Exercise object
     */
    public void addExercise(Exercise exercise){
        exerciseList.add(exercise);
    }

    /**
     * Method for getting an Exercise from the Planner's exerciseList, by comparing the string
     * entered with the names of the exercises in the list
     * @param wantedExercise The name of the Exercise which is sought for
     * @return The Exercise object if found, otherwise null
     */
    public Exercise getExercise(String wantedExercise){
        for (Exercise exercise : exerciseList){
            if (exercise.getName().equals(wantedExercise)){
                return exercise;
            }
        }
        return null;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public List<Routine> getRoutineList() {
        return routineList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }
}
