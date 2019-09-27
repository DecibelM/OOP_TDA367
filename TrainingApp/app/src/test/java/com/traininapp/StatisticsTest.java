package com.traininapp;

import com.traininapp.Model.Statistic;
import com.traininapp.Model.ISessionObserver;
import com.traininapp.Model.Exercise;
import com.traininapp.Model.CardioExercise;
import com.traininapp.Model.StrengthExercise;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Here are the tests for the classes "Statistic", "Session", "Exercise" and "ISessionObserver"
 */


public class StatisticsTest {


    private Statistic statistic = new Statistic();
    private StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
    private CardioExercise cardioExercise = new CardioExercise("5km",2,5);
    private List <Exercise> exerciseList = new ArrayList<Exercise>();


    @Test
    public void testTotalWeight(){
        exerciseList.add(strengthExercise);
        statistic.updateSessionStats(exerciseList);

        assertEquals(20, statistic.getTotWeightLifted());
    }

    @Test
    public void testTotalReps(){
        exerciseList.add(strengthExercise);
        exerciseList.add(strengthExercise);
        statistic.updateSessionStats(exerciseList);

        assertEquals(4,statistic.getTotReps());
    }



    @Test
    public void testTotalDistance(){
        exerciseList.add(cardioExercise);
        statistic.updateSessionStats(exerciseList);

        assertEquals(1,exerciseList.size());
        assertEquals(5,statistic.getTotDistance());
    }

    @Test
    public void testStrengthExercise(){

        assertEquals(10, strengthExercise.getWeight());
    }


}
