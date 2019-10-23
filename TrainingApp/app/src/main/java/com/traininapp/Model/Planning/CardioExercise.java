package com.traininapp.Model.Planning;

/**
 * Class CardioExercise is a class for cardio exercises.
 */
public class CardioExercise extends Exercise{

    private double runningTime;
    private double distance;

    /**
     * Constructor for cardio exercise
     * @param name
     * @param runningTime
     * @param distance
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

    public void setRunningTime(double runningTime) {
        this.runningTime = runningTime;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
