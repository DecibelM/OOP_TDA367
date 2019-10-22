package com.traininapp.Model;

import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;

import java.util.List;

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

    public User getUser(){
        return user;
    }
    //Tillfälligt bort det här
    /*public List<IGoal> getGoalList() {
        return user.getGoalList();
    }

    public List<IStat> getStatList(){ return user.getStatList(); } */
}
