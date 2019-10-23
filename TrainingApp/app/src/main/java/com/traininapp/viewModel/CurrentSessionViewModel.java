package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;


/**
 * Class CalendarViewModel is a class representing the viewmodel for the calendar.
 */
public class CurrentSessionViewModel extends ViewModel {

    // TODO Javadoc döp om "model"

    private Repository model;

    public CurrentSessionViewModel() {
        this.model = Repository.getInstance();
    }

    public Repository getModel() {
        return model;
    }

    public Session getSession(String sessionID){


        for (Session session : model.getUser().getPlanner().getSessionList()) {
            if (session.toString().equals(sessionID)){
                return session;
            }

        } return null;
    }
}