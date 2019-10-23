package com.traininapp;

import com.traininapp.Model.*;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.viewModel.CalendarViewModel;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CalendarViewModelTest {

    @Test
    public void getSessionsByDateTest(){
        Repository m = Repository.getInstance();
        CalendarViewModel cvm = new CalendarViewModel();
        StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
        CardioExercise cardioExercise = new CardioExercise("5km",2,5);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(strengthExercise);
        exerciseList.add(cardioExercise);

        Planner planner = m.getUser().getPlanner();
        planner.addSession(new Session("Löpning", exerciseList,LocalDate.of(2019,10,7)) );
        planner.addSession(new Session("Yoga", exerciseList, LocalDate.of(2019,10,8)));
        planner.addSession(new Session("Armträning", exerciseList, LocalDate.of(2019,10,9)));

        ArrayList<String> list = cvm.getSessionsByDateString(LocalDate.of(2019,10,7));
        assertEquals("Löpning", list.get(0));
    }
}
