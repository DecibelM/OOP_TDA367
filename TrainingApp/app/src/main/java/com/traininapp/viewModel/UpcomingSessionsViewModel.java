package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class UpcomingSessionsViewModel is a class representing the view model for the upcoming sessions
 * @author Mathias
 */
public class UpcomingSessionsViewModel extends ViewModel {

    // Singleton repo
    private Repository repo;

    /**
     * Constructor which fetches the singleton repo
     */
    public UpcomingSessionsViewModel() {
        this.repo = Repository.getInstance();
    }

    /**
     * Method which fetches the Planner's list of Sessions
     *
     * @return Planner's list of Sessions
     */
    public List<Session> getListOfSessions(){

        return repo.getSessionList();
    }
/*
    public void addSessionToList(String name, List<Exercise> exerciseList, LocalDate date){
        repo.addSession(name, exerciseList, date);
    }

 */

    /**
     * Sorts the Planner's list of Sessions by date, and removes all Sessions which date precede
     * today's date.
     * @return A sorted list with coming Sessions
     */
    public List<Session> getSortedSessionList() {

        List<Session> sortedSessions = new ArrayList<>();

        // Add Sessions which have date of today or later to list, and has not been finished
        for (Session session : getListOfSessions()){

            if ((session.getDate().isEqual(LocalDate.now()) || session.getDate().isAfter(LocalDate.now())) && !session.isFinished()){

                sortedSessions.add(session);
            }
        }

        // Sort the added Sessions by date
        Collections.sort(sortedSessions);

        return sortedSessions;
    }
}
