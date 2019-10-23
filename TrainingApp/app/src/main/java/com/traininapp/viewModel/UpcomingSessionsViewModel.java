package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    /**
     * Sorts the Planner's list of Sessions by date, and removes all Sessions which date precede
     * today's date.
     * @return A sorted list with coming Sessions
     */
    public List<Session> getSortedSessionList(){

        List<Session> sortedSessions = new ArrayList<>();

        // Add Sessions which have date of today or later to list
        for (Session session : getListOfSessions()){

            if (session.getDate().isEqual(LocalDate.now()) || session.getDate().isAfter(LocalDate.now())){

                sortedSessions.add(session);

            }
        }

        // Sort the added Sessions by date
        //Collections.sort(sortedSessions);

        return sortedSessions;
    }

}
