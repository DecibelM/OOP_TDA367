package com.traininapp.Model;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;
import com.traininapp.Model.Statistics.Results;

import java.time.LocalDate;
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

    public void addSession(String name, List<Exercise> exerciseList, LocalDate date){
        Session s = new Session(name, exerciseList,date);
        s.addObserver(results);
        planner.getSessionList().add(s);
    }

    List<IGoal> getGoalList() {
        return results.getGoalList();
    }

    List<IStat> getStatList(){ return results.getStatList(); }

    public Planner getPlanner() {
        return planner;
    }

    Results getResults() {
        return results;
    }

    public void createGoal(String name, Double target) {
        results.createGoal(name, target);
    }
}
