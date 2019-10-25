package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class CalendarViewModel is a class representing the ViewModel for the calendar.
 * Author Adam Törnkvist
 */
public class CalendarViewModel extends ViewModel {

    private Repository repository;

    public CalendarViewModel() {
        this.repository = Repository.getInstance();
    }

    /**
     * Method calls the repository and returns a list of the sessions on the date
     * it recieved as input.
     * @param date The date of interest.
     * @return a list of sessions connected to that date.
     */
    public ArrayList<String> getSessionsByDateString(LocalDate date){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Session> sessionList = (ArrayList<Session>) repository.getSessionList();

        for(Session s: sessionList){
            if (s.getDate().equals( date)){
                list.add(s.getName());
            }
        }
        return list;
    }

    /**
     * Gets a specific session on a specific date
     * @param i position on the session in the list
     * @param localDate Selected date
     * @return a session in position i of all the sessions on the selected date
     */
    public Session getSession(int i, LocalDate localDate){

        ArrayList<Session> list = new ArrayList<>();
        ArrayList<Session> sessionList = (ArrayList<Session>) repository.getSessionList();

        for(Session s: sessionList){
            if (s.getDate().equals( localDate)){
                list.add(s);
            }
        } return list.get(i);
    }

    /**
     * Finds all sessions on the selected date
     * @param localDate Selected date
     * @return a list of all sessions on the selected date
     */
    public ArrayList<Session> getSessionListByDate(LocalDate localDate) {
        ArrayList<Session> sessionByDateList = new ArrayList<>();
        ArrayList<Session> sessionList = (ArrayList<Session>) repository.getSessionList();

        for (Session s : sessionList) {
            if (s.getDate().equals(localDate)) {
                sessionByDateList.add(s);
            }
        }
        return sessionByDateList;
    }
}
