package com.traininapp.Model.Statistics;

import com.traininapp.Model.ISessionObserver;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * This handles the bulk of the statistics controll and keeps an eye onall of it.
 * It currently gets the data via an observer but in the future it should preferably collect it from a database.
 * Auth: Viktor Fredholm
 */
public class Results implements ISessionObserver {


    private List<Goal> goalList;
    private List<ExerciseSpecificStatistic> exerciseStatistics;

    private double totTrainingTime;
    private double totDistance;
    private int totReps;
    private int totSets;
    private double totWeightLifted;
    private double maxWeight;

    public Results() {

        goalList = new ArrayList<>();
        exerciseStatistics = new ArrayList<>();

        totTrainingTime = 0;
        totDistance = 0;
        totReps = 0;
        totSets = 0;
        totWeightLifted = 0;
        maxWeight = 0;

    }

    /**
     * This is called through the listener from a session, this method takes a list of exercises and hands them out separatley to the right method to handle the data.
     *
     * @param exerciseList The list of exercises coming from a finished session
     */
    @Override
    public void updateSessionStats(List<Exercise> exerciseList) {

        for (Exercise exercise: exerciseList){
            if (exercise instanceof CardioExercise){
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
    private void updateStrengthExercise(StrengthExercise exercise) {

        double tempWeight = exercise.getWeight();
        if (this.maxWeight < tempWeight) {
            maxWeight = tempWeight;
        }

        totSets = totSets + exercise.getSets();

        totReps = totReps + exercise.getReps();

        totWeightLifted = totWeightLifted + exercise.getWeight()*exercise.getReps()*exercise.getSets();

        updateExerciseList(exercise.getName(), exercise.getWeight(), System.currentTimeMillis());

        updateStrengthGoal(exercise);


    }

    /**
     * Takes in an exercise of the type 'Cardio' to update the relevant information in the Statistics class.
     * Preferably in the future it should also split the information based on exercise type and divide it accordingly.
     * @param exercise
     */
    private void updateCardioExercise(CardioExercise exercise){

        totTrainingTime = totTrainingTime + exercise.getRunningTime();

        totDistance = totDistance + exercise.getDistance();

        updateExerciseList(exercise.getName(), exercise.getDistance(), System.currentTimeMillis());

        updateCardioGoal(exercise);

    }

    /**
     * Updates the information for any matching 'Strength' goal
     *
     * @param exercise the exercise to read data from when adding to the goal
     */
    private void updateStrengthGoal(StrengthExercise exercise) {
        for (Goal goal: goalList){
            if (goal.getName().equals(exercise.getName()) || (goal.getProgress() <= exercise.getWeight())){
                goal.updateProgress(exercise.getWeight());
            }
        }
    }

    /**
     * Updates the information for any matching 'Cardio' goal
     *
     * @param exercise the exercise to read data from when adding to the goal
     */
    private void updateCardioGoal(CardioExercise exercise) {
        for (Goal goal: goalList){
            if (goal.getName().equals(exercise.getName())){
                goal.updateProgress((goal.getProgress()+exercise.getDistance()));
            }
        }
    }

    /**
     * Updates the Exercise specific data in the list
     * @param name Name of the exercise
     * @param data The weight or distance for the exercise
     * @param date Date of the exercise
     */
    private void updateExerciseList(String name, Double data, Long date){


        for (ExerciseSpecificStatistic exerciseStat: exerciseStatistics){
            if (name.equals(exerciseStat.getName())){
                exerciseStat.addToDataList(data, date);
                return;
            }
        }
        exerciseStatistics.add(new ExerciseSpecificStatistic(name, data, date));
    }

    public List<IStat> getStatList(){
        List<IStat> statList = new ArrayList<>();
        statList.addAll(exerciseStatistics);
        return statList;
    }

    public List<IGoal> getGoalList(){
        List<IGoal> list = new ArrayList<>();
        list.addAll(goalList);
        return list;
    }

    /**
     * Creates a goal
     * @param name name of goal
     * @param target target of goal
     */
    public void createGoal(String name, Double target) {
        goalList.add(new Goal(name, target));
    }
}
