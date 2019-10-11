package com.traininapp;

import com.traininapp.Model.*;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.viewModel.CalendarViewModel;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarViewModelTest {

    @Test
    public void getSessionsByDateTest(){
        Model m = new Model();
        CalendarViewModel cvm = new CalendarViewModel(m);

        Planner planner = m.getUser().getPlanner();
        planner.addSession("Löpning", LocalDate.of(2019,10,7));
        planner.addSession("Yoga", LocalDate.of(2019,10,8));
        planner.addSession("Armträning", LocalDate.of(2019,10,9));

        ArrayList<String> list = cvm.getSessionsByDate(LocalDate.of(2019,10,7));
        assertEquals("Löpning", list.get(0));
    }
}
