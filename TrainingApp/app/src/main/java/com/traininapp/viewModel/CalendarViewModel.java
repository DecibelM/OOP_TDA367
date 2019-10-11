package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class CalendarViewModel is a class representing the viewmodel for the calendar.
 */
public class CalendarViewModel extends ViewModel {
    Repository model;

    public CalendarViewModel(Repository model) {
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
