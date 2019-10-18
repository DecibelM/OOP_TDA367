package com.traininapp.Model;

import com.traininapp.Model.Planning.Planner;
import com.traininapp.viewModel.IStatistic;

import java.util.List;

public class Repository {

    private User user;

    private Repository() {
        this.user = new User(new Planner());
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


}
