package com.traininapp.Model;

public class StrengthExercise extends Exercise {
    private int sets;
    private int reps;
    private int weight;

    public StrengthExercise(String name, int sets, int reps, int weight) {
        super(name);
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
}
