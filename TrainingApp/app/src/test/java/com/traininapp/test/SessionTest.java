package com.traininapp.test;

import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SessionTest {

    private Repository repo = Repository.getInstance();
    private UpcomingSessionsViewModel viewModel = new UpcomingSessionsViewModel();
    private Planner planner = repo.getUser().getPlanner();

    private List<Exercise> exerciseList = new ArrayList<>();

    private Session session = new Session("Session 2", exerciseList, LocalDate.now());

    @Test
    public void finishSession() {

        session.finishSession();

        assertTrue(session.isFinished());
    }

    @Test
    public void compareTo() {

        List<Exercise> exerciseList1 = new ArrayList<>();

        StrengthExercise bicep = new StrengthExercise("Biceps", 1,1,1);
        CardioExercise running = new CardioExercise("Walking", 1,1);

        exerciseList1.add(bicep);
        exerciseList1.add(running);

        Session session1 = new Session("Session 1", exerciseList1, LocalDate.now().minusDays(1));
        Session session2 = new Session("Session 2", exerciseList1, LocalDate.now());
        Session session3 = new Session("Session 3", exerciseList1, LocalDate.now());
        Session session4 = new Session("Session 4", exerciseList1, LocalDate.now().plusDays(1));

        assertEquals("comparable", -1, session1.compareTo(session2));
        assertEquals("comparable", 0, session2.compareTo(session2));
        assertEquals("comparable", -2, session1.compareTo(session4));
        assertEquals("comparable", 0, session3.compareTo(session2));
    }
}