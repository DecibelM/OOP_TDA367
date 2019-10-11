package com.traininapp.Model;

import com.traininapp.Model.Planning.Planner;

public class Repository {

    private User user;

    public Repository() {
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
