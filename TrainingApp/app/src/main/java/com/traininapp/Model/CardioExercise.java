package com.traininapp.Model;

/**
 * Class CardioExercise is a class for cardio exercises.
 */
public class CardioExercise extends Exercise{
    private int runningTime;
    private int distance;

    public CardioExercise(String name, int runningTime, int distance) {
        super(name);
        this.runningTime = runningTime;
        this.distance = distance;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public int getDistance() {
        return distance;
    }
}
