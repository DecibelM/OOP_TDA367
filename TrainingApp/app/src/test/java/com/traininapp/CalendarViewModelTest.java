package com.traininapp;

import com.traininapp.viewModel.CalendarViewModel;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarViewModelTest {

    @Test
    public void getSessionsByDateTest(){
        CalendarViewModel cvm = new CalendarViewModel();

        ArrayList<String> list = cvm.getSessionsByDate(LocalDate.of(2019,10,04));
        assertEquals("Löpning", list.get(0));
    }
}
