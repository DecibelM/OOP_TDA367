package com.traininapp.Model;

public class Model {
    private User user;

    public Model() {
        this.user = new User(new Planner());
    }

    public User getUser() {
        return user;
    }
}
