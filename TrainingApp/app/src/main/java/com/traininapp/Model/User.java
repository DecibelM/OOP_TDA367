package com.traininapp.Model;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;
import com.traininapp.Model.Statistics.Results;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    //TODO kan man göra metoderna package private?
/**
 * Class User which represents the User.
 * It holds the information which belongs to the User
 * such as the Planner, list of goals and list of routines.
 */
public class User {

    private Planner planner;
    private Results results;

    /**
     * Constructor for User class.
     *
     */
    public User() {
        this.planner = new Planner();
        this.results = new Results();
    }

    public void addSession(String name, List<Exercise> eList, LocalDate date){
        Session s = new Session(name, eList,date);
        s.addObserver(results);
        planner.getSessionList().add(s);
    }

    public List<IGoal> getGoalList() {
        return results.getGoalList();
    }

    public List<IStat> getStatList(){ return results.getStatList(); }

    public Planner getPlanner() {
        return planner;
    }


    public Results getResults() {
        return results;
    }
}
