package com.traininapp.Model.Planning;

import com.traininapp.Model.Repository;

import org.junit.Test;

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
    private List<Exercise> exerciseList = planner.getExerciseList();

    @Test
    public void addSession() {
    }

    @Test
    public void addSession1() {
    }

    @Test
    public void addRoutine() {
    }

    @Test
    public void addExercise() {
    }

    @Test
    public void getExercise() {

        // Creating Exercise objects
        Exercise barbellrow = new Exercise("Barbell row");
        Exercise squat = new Exercise("Squat");
        Exercise deadlift = new Exercise("Deadlift");

        // Adding Exercises to Planner's list
        exerciseList.add(barbellrow);
        exerciseList.add(squat);
        exerciseList.add(deadlift);

        // Creating test Exercise object
        Exercise barbellrowTest = planner.getExercise("Barbell row");

        // Testing if barbellrowTest is same as barbellrow
        assertEquals(barbellrowTest, barbellrow);
        assertNotEquals(barbellrowTest, squat);
    }

    @Test
    public void getSessionList() {
    }

    @Test
    public void getRoutineList() {
    }

    @Test
    public void getExerciseList() {
    }
}