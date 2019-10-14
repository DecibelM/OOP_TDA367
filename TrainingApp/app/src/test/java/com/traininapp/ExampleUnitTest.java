package com.traininapp;

import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.Session;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addSessionTest(){
        Planner c = new Planner();
        LocalDate date = LocalDate.now();
        c.addSession("Magpass", date);
        assertEquals(1,c.getSessionList().size());
    }

    @Test
    public void addCardioExercise(){
        Planner c = new Planner();
        LocalDate date = LocalDate.now();
        List<Exercise> exerciseList = new ArrayList<>();
        List<Routine> routineList = new ArrayList<>();
        Routine routine = new Routine("Testroutine", exerciseList);
        routineList.add(routine);
        Session session = new Session("MagPass", routineList, date);
        session.addCardioExercise("Uppvarmning", 20, 2);
        List<Exercise> list = session.getExerciseList();

        assertEquals("Uppvarmning", list.get(0).getName());
    }
}