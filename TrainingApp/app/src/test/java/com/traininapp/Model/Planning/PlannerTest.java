package com.traininapp.Model.Planning;

import com.traininapp.Model.Repository;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlannerTest {

    // Initializing the singleton repo
    Repository repo = Repository.getInstance();

    // Fetching the Users planner
    Planner planner = repo.getUser().getPlanner();

    private List<Session> sessionList = new ArrayList<>();
    private List<Routine> routineList = new ArrayList<>();

    @Test
    public void addSession() {
        StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
        CardioExercise cardioExercise = new CardioExercise("5km",2,5);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(strengthExercise);
        exerciseList.add(cardioExercise);

        planner.addSession("hej", exerciseList, LocalDate.now());
        assertEquals(1,planner.getSessionList().size());
    }


}