package com.traininapp;

import com.traininapp.Model.Planner;
import com.traininapp.Model.Exercise;
import com.traininapp.Model.Session;

import org.junit.Test;

import java.time.LocalDate;
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
        Session session = new Session("MagPass", date);
        session.addCardioExercise("Uppvarmning", 20, 2);
        List<Exercise> list = session.getExerciseList();

        assertEquals("Uppvarmning", list.get(0).getName());
    }
}