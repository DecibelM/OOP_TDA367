package com.traininapp.Model.Planning;

/**
 * StrengthExercise is a class for exercises involving strength.
 */
public class StrengthExercise extends Exercise {

    private int sets;
    private int reps;
    private double weight;

    /**
     * Contructor for strength exercise
     * @param name Exercise name
     * @param sets Number of sets
     * @param reps Number of reps
     * @param weight Weight of exercise
     */
    public StrengthExercise(String name, int sets, int reps, double weight) {
        super(name);
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int getSets(){
        return sets;
    }

    public int getReps(){
        return reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
