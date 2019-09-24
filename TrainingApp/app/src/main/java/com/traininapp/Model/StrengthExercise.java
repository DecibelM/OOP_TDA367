package com.traininapp.Model;

/**
 * StrengthExercise is a class for exercises involving strength.
 */
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


    public int getWeight() {
        return weight;
    }

    public int getSets(){
        return sets;
    }

    public int getReps(){
        return reps;
    }

}
