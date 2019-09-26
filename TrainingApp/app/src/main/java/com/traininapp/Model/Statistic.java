package com.traininapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Statistic implements ISessionObserver {

    int totTrainingTime;
    int totDistance;
    int totReps;
    int totSets;
    int totWeightLifted;
    int maxWeight;

    public void Statistic(){
        totTrainingTime = 0;
        totDistance =0;
        totReps = 0;
        totSets = 0;
        totWeightLifted = 0;
        maxWeight = 0;
    }

    @Override
    public void updateSessionStats(List<Exercise> exerciseList) {
        List<CardioExercise> cardioExercises = new ArrayList<>();
        List<StrengthExercise> strengthExercises = new ArrayList<>();

        for (Exercise exercise: exerciseList){                      //TODO separate cardio from strength exercises ant send to different methods accordingly
           // updateCardioExercise();
            if (exercise instanceof CardioExercise){            //instanceof checks if the exercise is of the class in question
                cardioExercises.add((CardioExercise) exercise);
            } else if (exercise instanceof StrengthExercise){
                strengthExercises.add((StrengthExercise) exercise);
            } else {
                //TODO throw the right type of exception for non implemented exercise type
                System.out.println("there is no file of this type");
            }
        }
    }

    private void updateStrengthExercise(StrengthExercise exercise) {    //TODO this should also update the statistics of a specific exercise

        int tempWeight = exercise.getWeight();
        if (this.maxWeight < tempWeight) {
            maxWeight = tempWeight;
        }

        totSets = totSets + exercise.getSets();

        totReps = totReps + exercise.getReps();

        totWeightLifted = totWeightLifted + exercise.getWeight()*exercise.getReps()*exercise.getSets();
    }

    private void updateCardioExercise(CardioExercise exercise){         //TODO this should also update the statistics of a specific exercise

        totTrainingTime = totTrainingTime + exercise.getRunningTime();

        totDistance = totDistance + exercise.getDistance();
    }
}
