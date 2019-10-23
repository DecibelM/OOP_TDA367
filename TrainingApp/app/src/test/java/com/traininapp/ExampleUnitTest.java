package com.traininapp;

import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addSessionTest(){
        Planner c = new Planner();
        LocalDate date = LocalDate.now();
        StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
        CardioExercise cardioExercise = new CardioExercise("5km",2,5);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(strengthExercise);
        exerciseList.add(cardioExercise);
        c.addSession(new Session("Magpass", exerciseList, date));
        assertEquals(1,c.getSessionList().size());
    }
}