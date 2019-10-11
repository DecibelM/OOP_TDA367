package com.traininapp.Model;

import java.util.List;

/**
 * Class Routine. Holds a list of exercises that the user wishes to save
 * and use later when creating a new Session.
 */
public class Routine {
    private String name;
    private List<Exercise> savedExerciseList;

    /**
     * Constructor for Routine class
     * @param name Name of routine
     * @param savedExerciseList List of saved exercises.
     */
    public Routine(String name, List<Exercise> savedExerciseList) {
        this.name = name;
        this.savedExerciseList = savedExerciseList;
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getSavedExerciseList() {
        return savedExerciseList;
    }

    @Override
    public String toString() {
        return name;
    }
}
