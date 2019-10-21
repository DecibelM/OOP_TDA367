package com.traininapp.Model.Planning;

import java.util.ArrayList;
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

    // TODO Är det bra att kunna skapa en Routine utan lista med Exercises?
    /**
     * Constructor for Routine class with empty list of Exercises
     * @param name Name of Routine
     */
    public Routine(String name){
        this.name = name;
        this.savedExerciseList = new ArrayList<>();
    }

    /**
     * Method to add an Exercise to
     * @param exercise Which Exercise to add
     */
    public void addExerciseToList(Exercise exercise){
        savedExerciseList.add(exercise);
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
