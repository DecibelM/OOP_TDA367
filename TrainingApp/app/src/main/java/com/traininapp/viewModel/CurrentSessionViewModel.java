package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

/**
 * Class CalendarViewModel is a class representing the viewmodel for the calendar.
 */
public class CurrentSessionViewModel extends ViewModel {

    private Repository repo;

    public CurrentSessionViewModel() {
        this.repo = Repository.getInstance();
    }

    public Repository getRepo() {
        return repo;
    }

    public Session getSession(String sessionID){

        for (Session session : repo.getSessionList()) {
            if (session.toString().equals(sessionID)){
                return session;
            }
        }
        return null;
    }
}