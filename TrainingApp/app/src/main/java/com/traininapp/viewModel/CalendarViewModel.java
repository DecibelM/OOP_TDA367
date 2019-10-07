package com.traininapp.viewModel;

import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Model;
import com.traininapp.Model.Session;
import com.traininapp.R;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CalendarViewModel is a class representing the viewmodel for the calendar.
 */
public class CalendarViewModel extends ViewModel {
    Model model;

    public CalendarViewModel(Model model) {
        this.model = model;
    }

    /**
     * Method calls the model and returns a list of the sessions on the date
     * it recieved as input.
     * @param date The date of interest.
     * @return a list of sessions connected to that date.
     */
    public ArrayList<String> getSessionsByDate(LocalDate date){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Session> sessionList = (ArrayList<Session>) model.getUser().getPlanner().getSessionList();

        for(Session s: sessionList){
            if (s.getDate().equals( date)){
                list.add(s.getName());
            }
        }
        return list;
    }
}
