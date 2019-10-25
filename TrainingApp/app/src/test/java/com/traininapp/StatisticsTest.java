package com.traininapp;

import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Statistics.Results;
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


    private Results statistic = new Results();
    private StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
    private CardioExercise cardioExercise = new CardioExercise("5km",2,5);
    private List <Exercise> exerciseList = new ArrayList<Exercise>();

    @Test
    public void testStrengthExercise(){

        assertEquals(10, strengthExercise.getWeight(),0);
    }

    @Test
    public void testUpdateObserver(){
        //Session s = new Session("Birthdaywoho", LocalDate.of(2019, 11, 02), R.drawable.workout_1);
        User user = new User();
        exerciseList.add(cardioExercise);
        exerciseList.add(strengthExercise);
        user.addSession("Birthdaywoho",exerciseList, LocalDate.of(2019,11,2),R.drawable.workout_5);
        List<Session> sessionList = user.getPlanner().getSessionList();
        Session s = sessionList.get(0);

        s.finishSession();
        assertTrue(s.isFinished());
    }


}
