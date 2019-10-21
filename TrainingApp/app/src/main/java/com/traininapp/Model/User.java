package com.traininapp.Model;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;
import com.traininapp.Model.Statistics.Results;

import java.time.LocalDate;
import java.util.ArrayList;
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

    /**
     * Constructor for User class.
     * @param planner the planner containing planned sessions
     */
    public User( Planner planner) {
        this.planner = planner;
        routineList = new ArrayList<>();
        this.results = new Results();
    }

    public void addRoutine(String name, List<Exercise> exerciseList){
        routineList.add(new Routine(name, exerciseList));
    }

    public void addSession(String name, List<Exercise> eList, LocalDate date){
        Session s = new Session(name, eList,date);
        s.addObserver(results.getStatistic());
        planner.getSessionList().add(s);
    }

    public Results getResults() {
        return results;
    }



    public void removeRoutine(String name){
        for(Routine r : routineList){
            if(name.equals(r.getName())){
                routineList.remove(r);
            }
        }
    }

    public List<IGoal> getGoalList() {
        return results.getGoalList();
    }

    public List<IStat> getStatList(){ return results.getStatList(); }

    public Planner getPlanner() {
        return planner;
    }

    public List<Routine> getRoutineList() {
        return routineList;
    }


}
