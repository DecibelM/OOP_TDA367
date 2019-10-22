package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

import java.time.LocalDate;
import java.util.List;

public class UpcomingSessionsViewModel extends ViewModel {

    Repository model;

    public UpcomingSessionsViewModel(){
        this.model = Repository.getInstance();
    }

    public List<Session> getListOfSessions(){

        return model.getUser().getPlanner().getSessionList();
    }

    public void addSessionToList(String name, List<Exercise> exerciseList, LocalDate date){
        model.getUser().addSession(name, exerciseList, date);
    }

}
