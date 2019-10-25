package com.traininapp.test;

import android.media.Image;

import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SessionTest {

    private List<Exercise> exerciseList = new ArrayList<>();
    private Session session = new Session("Session 2", LocalDate.now(), exerciseList, R.drawable.workout_3);

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
        int image = R.drawable.workout_3;

        exerciseList1.add(bicep);
        exerciseList1.add(running);

        Session session1 = new Session("Session 1",  LocalDate.now().minusDays(1), exerciseList1, image);
        Session session2 = new Session("Session 2", LocalDate.now(), exerciseList1, image);
        Session session3 = new Session("Session 3", LocalDate.now(), exerciseList1, image);
        Session session4 = new Session("Session 4", LocalDate.now().plusDays(1), exerciseList1,image);

        assertEquals("comparable", -1, session1.compareTo(session2));
        assertEquals("comparable", 0, session2.compareTo(session2));
        assertEquals("comparable", -2, session1.compareTo(session4));
        assertEquals("comparable", 0, session3.compareTo(session2));
    }
}