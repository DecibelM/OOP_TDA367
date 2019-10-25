package com.traininapp.Model.Planning;

/**
 * Class CardioExercise is a class for cardio exercises.
 * @author Maria Fornmark
 */
public class CardioExercise extends Exercise{

    private double runningTime;
    private double distance;

    /**
     * Constructor for cardio exercise
     * @param name Name of CardioExercise
     * @param runningTime The duration of the CardioExercise
     * @param distance The distance of the CardioExercise
     */
    public CardioExercise(String name, double runningTime, double distance) {
        super(name);
        this.runningTime = runningTime;
        this.distance = distance;
    }

    public double getRunningTime() {
        return runningTime;
    }

    public double getDistance() {
        return distance;
    }
}
