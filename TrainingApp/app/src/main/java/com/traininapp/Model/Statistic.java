package com.traininapp.Model;

import java.util.List;

public class Statistic implements ISessionObserver {

    int totTrainingTime;
    int totDistance;
    int totalAmmountReps;
    int totalAmmountSets;
    int maxWeight;

    public void Statistic(){
        totTrainingTime = 0;
        totDistance =0;
        totalAmmountReps = 0;
        totalAmmountSets = 0;
        maxWeight = 0;
    }

    @Override
    public void updateCardioSessionStats(List<CardioExercise> exerciseList) {
        for (CardioExercise exercise: exerciseList){
            updateCardioExercise(exercise);
        }
    }

    @Override
    public void updateWeightSessionStats(List<StrengthExercise> exerciseList) {
        for (StrengthExercise exercise: exerciseList){
            updateStrengthExercise(exercise);
        }
    }

    private void updateStrengthExercise(StrengthExercise exercise) {    //TODO this should also update the statistics of a specific exercise

        int tempWeight = exercise.getWeight();
        if (this.maxWeight < tempWeight) {
            maxWeight = tempWeight;
        }

        totalAmmountSets = totalAmmountSets + exercise.getSets();

        totalAmmountReps = totalAmmountReps + exercise.getReps();
    }

    private void updateCardioExercise(CardioExercise exercise){         //TODO this should also update the statistics of a specific exercise

        totTrainingTime = totTrainingTime + exercise.getRunningTime();

        totDistance = totDistance + exercise.getDistance();
    }
}
