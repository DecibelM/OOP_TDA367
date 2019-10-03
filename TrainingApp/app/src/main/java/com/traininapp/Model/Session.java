package com.traininapp.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Session which holds a list of exercises and a date.s
 */
public class Session {
    private String name;
    private List<Exercise> exerciseList;
    LocalDate date;

    public Session(String name, LocalDate date) {
        this.name = name;
        this.exerciseList = new ArrayList<>();
        this.date = date;
    }

    /**
     * Method adds a Cardio Exercise into a Session.
     * @param name name of added exercise
     * @param runningTime time running (min)
     * @param distance distance run (m)
     */
    public void addCardioExercise(String name, int runningTime, int distance ){
        exerciseList.add(new CardioExercise(name, runningTime, distance));
    }

    /**
     * Method adds a Strength exercise into a Session.
     * @param name name of added exercise
     * @param sets number of sets
     * @param reps number of reps
     * @param weight weight used in the exercise
     */
    public void addStrengthExercise(String name, int sets, int reps, int weight ){
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
}
