package com.traininapp.test;

import com.traininapp.Model.Repository;
import com.traininapp.R;

import org.junit.Test;

import java.time.LocalDate;


import static org.junit.Assert.*;

public class UpcomingSessionsViewModelTest {

    Repository repo = Repository.getInstance();

    @Test
    public void getListOfSessions() {
    }

    @Test
    public void addSessionToList() {

        repo.getUser().getPlanner().addSession("Löpning", LocalDate.now(), R.drawable.workout_5);
        repo.getUser().getPlanner().addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        repo.getUser().getPlanner().addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        repo.getUser().getPlanner().addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);

        assertEquals(4, repo.getUser().getPlanner().getSessionList().size());
    }
}