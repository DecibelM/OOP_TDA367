package com.traininapp.test;

import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;


import static org.junit.Assert.*;

public class UpcomingSessionsViewModelTest {

    private Repository repo = Repository.getInstance();
    private Planner planner = repo.getUser().getPlanner();

    @Test
    public void getListOfSessions() {

        planner.getSessionList().clear();

        UpcomingSessionsViewModel viewModel = new UpcomingSessionsViewModel();

        planner.addSession("Löpning", LocalDate.now().minusDays(1),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now(),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(1),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(2),R.drawable.workout_1);

        List<Session> list = viewModel.getListOfSessions();

        assertEquals(4, list.size());
    }

    @Test
    public void addSessionToList() {

        planner.getSessionList().clear();

        planner.addSession("Löpning", LocalDate.now(), R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);

        assertEquals(4, planner.getSessionList().size());
    }
}