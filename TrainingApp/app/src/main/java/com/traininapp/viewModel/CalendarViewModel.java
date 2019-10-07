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

public class CalendarViewModel extends ViewModel {
    //List<Session> sessionList;
    Model model;

    public CalendarViewModel(Model model) {
        //this.sessionList = new ArrayList<>();
        //sessionList.add(new Session("Löpning", LocalDate.of(2019,10,04)));
        //sessionList.add(new Session("Yoga", LocalDate.of(2019,10,05)));
        //sessionList.add(new Session("Mage", LocalDate.of(2019,10,06)));
        this.model = model;
    }

    public ArrayList<String> getSessionsByDate(LocalDate date){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Session> sessionList = (ArrayList<Session>) model.getUser().getPlanner().getSessionList();

        for(Session s: sessionList){
            LocalDate sessionDate = s.getDate();
            if (s.getDate().equals( date)){
                list.add(s.getName());
            }
        }

        return list;
    }
}
