package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Model;
import com.traininapp.Model.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpcomingSessionsViewModel extends ViewModel {

    Model model;

    public UpcomingSessionsViewModel(Model model){
        this.model = model;
    }

    public List<Session> getListOfSessions(){

        return model.getUser().getPlanner().getSessionList();
    }

}
