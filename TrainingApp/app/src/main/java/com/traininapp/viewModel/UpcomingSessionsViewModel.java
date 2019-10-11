package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

import java.time.LocalDate;
import java.util.List;

public class UpcomingSessionsViewModel extends ViewModel {

    Repository model;

    public UpcomingSessionsViewModel(Repository model){
        this.model = model;
    }

    public List<Session> getListOfSessions(){

        return model.getUser().getPlanner().getSessionList();
    }

    public void addSessionToList(String name, LocalDate date){
        model.getUser().getPlanner().addSession(name, date);
    }

}
