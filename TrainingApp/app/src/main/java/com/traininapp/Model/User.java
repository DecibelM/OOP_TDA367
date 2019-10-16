package com.traininapp.Model;

import com.traininapp.Model.Planning.*;
import com.traininapp.Model.Statistics.Goal;
import com.traininapp.Model.Statistics.Results;

import java.time.LocalDate;
import java.util.List;

/**
 * Class User which represents the User.
 * It holds the information which belongs to the User
 * such as the Planner, list of goals and list of routines.
 */
public class User {

    private Planner planner;
    private List<Routine> routineList;
    private Results results;
    private List<Routine> routineList = new ArrayList<>();
    private Routine routine;

    /**
     * Constructor for User class.
     * @param planner the planner containing planned sessions
     */
    public User( Planner planner) {
        this.planner = planner;
        this.results = new Results();
    }

    public void addRoutine(String name, List<Exercise> exerciseList){
        routineList.add(new Routine(name, exerciseList));
    }

    public void removeRoutine(String name){
        for(Routine r : routineList){
            if(name.equals(r.getName())){
                routineList.remove(r);
            }
        }
    }

    public void addSession(String name, LocalDate date, int image){
        Session s = new Session(name, date, image);
        s.addObserver(results.getStatistic());
        planner.getSessionList().add(s);
    }

    public Results getResults() {
        return results;
    }

    public Planner getPlanner() {
        return planner;
    }
    public List<Routine> getRoutineList() {
        return routineList;
    }
}
