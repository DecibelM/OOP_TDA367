package com.traininapp.Model;

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

}
