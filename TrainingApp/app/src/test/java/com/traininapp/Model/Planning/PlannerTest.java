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
    public void getSessionList() {
    }

    @Test
    public void getRoutineList() {
    }

    @Test
    public void getExerciseList() {
    }
}