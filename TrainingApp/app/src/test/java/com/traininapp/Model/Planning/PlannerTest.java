package com.traininapp.Model.Planning;

import com.traininapp.Model.Repository;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlannerTest {



    @Test
    public void addSession() {
        // Initializing the singleton repo
        Repository r = Repository.getInstance();


        // Fetching the Users planner
        Planner p = r.getUser().getPlanner();

        StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
        CardioExercise cardioExercise = new CardioExercise("5km",2,5);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(strengthExercise);
        exerciseList.add(cardioExercise);

        p.addSession("hej", exerciseList, LocalDate.now());
        assertEquals(1,p.getSessionList().size());
    }


}