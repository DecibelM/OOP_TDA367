package com.traininapp.test;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class UpcomingSessionsViewModelTest {

    private Repository repo = Repository.getInstance();

    @Test
    public void getListOfSessions() {

        repo.getSessionList().clear();
        UpcomingSessionsViewModel viewModel = new UpcomingSessionsViewModel();
        List <Exercise> eList = new ArrayList<>();

        repo.addSession("Löpning",eList,LocalDate.now().minusDays(1),R.drawable.workout_5);
        repo.addSession("Yoga",eList, LocalDate.now(),R.drawable.workout_2);
        repo.addSession("Armträning",eList, LocalDate.now().plusDays(1),R.drawable.workout_4);
        repo.addSession("Hjärngympa",eList, LocalDate.now().plusDays(2),R.drawable.workout_1);

        List<Session> list = viewModel.getListOfSessions();

        assertEquals(4, list.size());
    }

    @Test
    public void addSessionToList() {

        repo.getSessionList().clear();
        List <Exercise> eList = new ArrayList<>();

        repo.addSession("Löpning",eList,LocalDate.now().minusDays(1),R.drawable.workout_5);
        repo.addSession("Yoga",eList, LocalDate.now(),R.drawable.workout_2);
        repo.addSession("Armträning",eList, LocalDate.now().plusDays(1),R.drawable.workout_4);
        repo.addSession("Hjärngympa",eList, LocalDate.now().plusDays(2),R.drawable.workout_1);

        assertEquals(4, repo.getSessionList().size());
    }
}