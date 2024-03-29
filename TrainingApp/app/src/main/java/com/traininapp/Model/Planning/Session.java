package com.traininapp.Model.Planning;

import com.traininapp.Model.ISessionObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Session which holds a list of exercises and a date.
 * @author Maria Fornmark
 */
public class Session implements Comparable<Session>{

    private String name;
    private List<Exercise> exerciseList;
    private List<ISessionObserver> sessionObservers;
    private LocalDate date;
    private int sessionImage;
    private boolean isFinished = false;

    /**
     * Constructor for Session which takes name, date and image as parameters. Used for testing.
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
     * Constructor for creating a Session with name, date, exercise list, image and
     * @param name Name of Session
     * @param date Date of Session
     * @param exerciseList Exercise list of Session
     * @param sessionImage Image of Session
     */
    public Session(String name, LocalDate date, List<Exercise> exerciseList, int sessionImage, boolean isFinished) {
        this.name = name;
        this.exerciseList = exerciseList;
        this.date = date;
        this.sessionImage = sessionImage;
        this.isFinished = isFinished;
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
     *Sends the list of exercises over to the observer
     * Call for this only once! otherwise there will be duplicate data in statistics.
     */
    private void updateSessionObserver(){
        for (ISessionObserver observer: sessionObservers){
            observer.updateSessionStats(exerciseList);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void finishSession(){
        isFinished = true;
        updateSessionObserver();
    }

    public boolean isFinished() {
        return isFinished;
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
     * Method used to compare the dates of two Sessions
     * @param session The Session to be compared with
     * @return The difference in days between the two Sessions, returned as int
     */
    @Override
    public int compareTo(Session session) {
        return this.getDate().compareTo(session.getDate());
    }
}
