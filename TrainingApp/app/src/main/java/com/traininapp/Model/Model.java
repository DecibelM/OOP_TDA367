package com.traininapp.Model;

import com.traininapp.Model.Planning.Planner;

public class Model {
    private User user;

    public Model() {
        this.user = new User(new Planner());
    }

    public User getUser() {
        return user;
    }
}
