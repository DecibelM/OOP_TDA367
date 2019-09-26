package com.traininapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This handles the bulk of the statistics controll and keeps an eye onall of it.
 * It currently gets the data via an observer but in the future it should preferably collect it from a database.
 */
public class Statistic implements ISessionObserver {

    private int totTrainingTime;
    private int totDistance;
    private int totReps;
    private int totSets;
    private int totWeightLifted;
    private int maxWeight;

    public Statistic(){
        totTrainingTime = 0;
        totDistance =0;
        totReps = 0;
        totSets = 0;
        totWeightLifted = 0;
        maxWeight = 0;
    }

    @Override
    public void updateSessionStats(List<Exercise> exerciseList) {

        for (Exercise exercise: exerciseList){
            if (exercise instanceof CardioExercise){            //'instanceof' checks if the 'exercise' is of the class 'CardioExercise'
                updateCardioExercise((CardioExercise) exercise);
            } else if (exercise instanceof StrengthExercise){
                updateStrengthExercise((StrengthExercise) exercise);
            } else {

                throw new IllegalArgumentException("The exercise must be of the type; 'CardioExercise' or 'StrengthExerxise'");
            }
        }
    }

    /**
     * Takes in an exercise of the type 'Strength' to update the relevant information in the Statistics class.
     * Preferably in the future it should also split the information based on exercise type and divide it accordingly.
     * @param exercise
     */
    private void updateStrengthExercise(StrengthExercise exercise) {    //TODO this should also update the statistics of a specific exercise

        int tempWeight = exercise.getWeight();
        if (this.maxWeight < tempWeight) {
            maxWeight = tempWeight;
        }

        totSets = totSets + exercise.getSets();

        totReps = totReps + exercise.getReps();

        totWeightLifted = totWeightLifted + exercise.getWeight()*exercise.getReps()*exercise.getSets();
    }

    /**
     * Takes in an exercise of the type 'Cardio' to update the relevant information in the Statistics class.
     * Preferably in the future it should also split the information based on exercise type and divide it accordingly.
     * @param exercise
     */
    private void updateCardioExercise(CardioExercise exercise){         //TODO this should also update the statistics of a specific exercise

        totTrainingTime = totTrainingTime + exercise.getRunningTime();

        totDistance = totDistance + exercise.getDistance();
    }

    /**
     *
     * @return the total traing time
     */
    public int getTotTrainingTime() {
        return totTrainingTime;
    }

    /**
     *
     * @return Total running distance
     */
    public int getTotDistance() {
        return totDistance;
    }

    /**
     *
     * @return total ammount of weight reps
     */
    public int getTotReps() {
        return totReps;
    }

    /**
     *
     * @return total ammount of weight sets
     */
    public int getTotSets() {
        return totSets;
    }

    /**
     * This is the total ammount of weight lifted, this is calculated by [weight]*[sets]*[reps]
     *
     * @return total weight lifted (int)
     */
    public int getTotWeightLifted() {
        return totWeightLifted;
    }

    /**
     *
     * @return Highest value of weight lifted
     */
    public int getMaxWeight() {
        return maxWeight;
    }
}
