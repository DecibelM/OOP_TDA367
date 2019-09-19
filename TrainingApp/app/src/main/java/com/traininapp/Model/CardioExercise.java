package com.traininapp.Model;

public class CardioExercise extends Exercise{
    private int runningTime;
    private int distance;

    public CardioExercise(String name, int runningTime, int distance) {
        super(name);
        this.runningTime = runningTime;
        this.distance = distance;
    }
}
