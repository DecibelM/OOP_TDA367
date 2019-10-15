package com.traininapp;

import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Statistics.Statistic;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.User;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Here are the tests for the classes "Statistic", "Session", "Exercise" and "ISessionObserver"
 */


public class StatisticsTest {

    private User user = new User(new Planner());
    //private Statistic statistic = new Statistic();
    private StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
    private CardioExercise cardioExercise = new CardioExercise("5km",2,5);
    private List <Exercise> exerciseList = new ArrayList<Exercise>();


    @Test
    public void testTotalWeight(){
        exerciseList.add(strengthExercise);
        user.getResults().getStatistic().updateSessionStats(exerciseList);

        assertEquals(20, user.getResults().getStatistic().getTotWeightLifted(),0);
    }

    @Test
    public void testTotalReps(){
        exerciseList.add(strengthExercise);
        exerciseList.add(strengthExercise);
        user.getResults().getStatistic().updateSessionStats(exerciseList);

        assertEquals(4,user.getResults().getStatistic().getTotReps());
    }



    @Test
    public void testTotalDistance(){
        exerciseList.add(cardioExercise);
        user.getResults().getStatistic().updateSessionStats(exerciseList);

        assertEquals(1,exerciseList.size());
        assertEquals(5,user.getResults().getStatistic().getTotDistance(),0);
    }

    @Test
    public void testStrengthExercise(){

        assertEquals(10, strengthExercise.getWeight(),0);
    }

    @Test
    public void testUpdateObserver(){
        //Session s = new Session("Birthdaywoho", LocalDate.of(2019, 11, 02), R.drawable.workout_1);
        user.addSession("Birthdaywoho", LocalDate.of(2019,11,2), R.drawable.workout_1);
        List<Session> sessionList = user.getPlanner().getSessionList();
        Session s = sessionList.get(0);
        s.addCardioExercise("RunRun", 20, 2);
        s.addStrengthExercise("Liftlift", 20, 20, 20);

        s.finishSession();
        assertTrue(s.isFinished());
        assertEquals(2, user.getResults().getStatistic().getTotDistance(),0);


    }


}
