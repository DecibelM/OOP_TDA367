package com.traininapp.Model;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository holds the model and
 * shares information about the model.
 */
public class Repository {

    private User user;

    private Repository() {
        this.user = new User();
    }

    private static class RepositoryHolder{
        private static Repository instance = new Repository();
    }

    public static Repository getInstance(){
        return RepositoryHolder.instance;
    }

    public void addSession(String name, List<Exercise> exerciseList, LocalDate date, int image){
        user.addSession(name, exerciseList,date,image);
    }

    public List<Session> getSessionList(){
        return user.getPlanner().getSessionList();
    }

    public List<IGoal> getGoalList() {
        return user.getGoalList();
    }

    public List<IStat> getStatList(){ return user.getStatList(); }
}
