package com.traininapp.Model.Planning;

import com.traininapp.Model.ISessionObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Session which holds a list of exercises and a date.s
 */
public class Session implements Comparable<Session>{
    private String name;
    private List<Exercise> exerciseList;
    private List<ISessionObserver> sessionObservers;
    LocalDate date;
    private int sessionImage;
    private boolean isFinished = false;

    /**
     * Constructor for Session which takes name of exercise and date as parameters
     * @param name Name of session
     * @param date Date of session
     */

    public Session(String name, List<Exercise> exList, LocalDate date) {
        this.name = name;
        this.exerciseList = new ArrayList<>();
        this.date = date;
        sessionObservers = new ArrayList<>();
    }

    /**
     * Constructor for Session which takes name, date and image as parameters
     * @param name Name of session
     * @param date Date of session
     * @param sessionImage Image of session
     */
    public Session(String name, LocalDate date, int sessionImage) {
        this.name = name;
        this.exerciseList = new ArrayList<>();
        this.date = date;
        this.sessionImage = sessionImage;
        sessionObservers = new ArrayList<>();
    }

    /**
     * Constructor for creating a Session with name, date, exercise list and image
     * @param name Name of Session
     * @param date Date of Session
     * @param exerciseList Exercise list of Session
     * @param sessionImage Image of Session
     */
    public Session(String name, LocalDate date, List<Exercise> exerciseList, int sessionImage) {
        this.name = name;
        this.exerciseList = exerciseList;
        this.date = date;
        this.sessionImage = sessionImage;
        sessionObservers = new ArrayList<>();
    }

    /**
     * Adds an observer to the session
     *
     * @param observer
     */
    public void addObserver(ISessionObserver observer){
        sessionObservers.add(observer);
    }

    /**
     * Removes an observer from the session
     *
     * @param observer
     */
    public void removeObservers(ISessionObserver observer){
        sessionObservers.remove(observer);
    }

    /**
     *Sends the list of exercises over to the observer
     * Call for this only once! otherwise there will be duplicate data in statistics.
     */
    private void updateSessionObserver(){
        for (ISessionObserver observer: sessionObservers){
            observer.updateSessionStats(exerciseList);
        }
    }

    public void finishSession(){
        isFinished = true;
        updateSessionObserver();
    }

    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Method adds a Cardio Exercise into a Session.
     * @param name name of added exercise
     * @param runningTime time running (min)
     * @param distance distance run (m)
     */
    public void addCardioExercise(String name, double runningTime, double distance ){
        exerciseList.add(new CardioExercise(name, runningTime, distance));
    }

    /**
     * Method adds a Strength exercise into a Session.
     * @param name name of added exercise
     * @param sets number of sets
     * @param reps number of reps
     * @param weight weight used in the exercise
     */
    public void addStrengthExercise(String name, int sets, int reps, double weight ){
        exerciseList.add(new StrengthExercise(name, sets, reps, weight));
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSessionImage() {
        return sessionImage;
    }

    /**
     * Method to compare the dates of two Sessions
     * @param session The Session to be compared with
     * @return -1 if earlier, 0 if same, 1 if later
     */
    @Override
    public int compareTo(Session session) {
        return this.getDate().compareTo(session.getDate());
    }
}
