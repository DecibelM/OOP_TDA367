package com.traininapp.test;

import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.viewModel.CalendarViewModel;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class UpcomingSessionsViewModelTest {

    @Test
    public void getListOfSessions() {

        Repository repo = Repository.getInstance();
        UpcomingSessionsViewModel viewModel = new UpcomingSessionsViewModel();
        Planner planner = repo.getUser().getPlanner();

        planner.addSession("Löpning", LocalDate.now().minusDays(1),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now(),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(1),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(2),R.drawable.workout_1);

        List<Session> list = viewModel.getListOfSessions();

        assertEquals(4, list.size());
    }

    @Test
    public void addSessionToList() {

        Repository repo = Repository.getInstance();
        Planner planner = repo.getUser().getPlanner();

        planner.addSession("Löpning", LocalDate.now(), R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);

        assertEquals(4, repo.getUser().getPlanner().getSessionList().size());
    }
}