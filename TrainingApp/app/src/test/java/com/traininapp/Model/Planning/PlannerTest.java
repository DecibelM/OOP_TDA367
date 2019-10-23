package com.traininapp.Model.Planning;

import com.traininapp.Model.Repository;
import com.traininapp.R;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlannerTest {



    @Test
    public void addSession() {
        // Initializing the singleton repo
        Planner p = new Planner();

        StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
        CardioExercise cardioExercise = new CardioExercise("5km",2,5);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(strengthExercise);
        exerciseList.add(cardioExercise);

        p.addSession(new Session("hej",  LocalDate.now(), exerciseList, R.drawable.workout_1));
        assertEquals(1,p.getSessionList().size());
    }


}