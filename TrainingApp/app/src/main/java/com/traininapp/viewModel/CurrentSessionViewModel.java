package com.traininapp.viewModel;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;
import com.traininapp.View.FragCarRow;
import com.traininapp.View.FragStrRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CalendarViewModel is a class representing the viewmodel for the calendar.
 */
public class CurrentSessionViewModel extends ViewModel {

    private Repository repo;
    private Session session;
    private Activity activity;

    private ArrayList<FragStrRow> listStrFrag;
    private ArrayList<FragCarRow> listCarFrag;
    private String sessionID;

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public CurrentSessionViewModel(String sessionID, Activity activity) {
        this.repo = Repository.getInstance();
        this.sessionID = sessionID;
        this.activity = activity;
        this.session = getCurrentSession();
        listCarFrag = new ArrayList<>();
        listStrFrag = new ArrayList<>();

    }

    public Repository getRepo() {
        return repo;
    }

    public Session getCurrentSession(){

        for (Session session : repo.getSessionList()) {
            if (session.toString().equals(sessionID)){
                return session;
            }
        }
        return null;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<FragStrRow> getListStrFrag() {
        return listStrFrag;
    }

    public List<FragCarRow> getListCarFrag() {
        return listCarFrag;
    }


}