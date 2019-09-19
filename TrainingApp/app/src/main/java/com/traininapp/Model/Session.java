package com.traininapp.Model;

import java.util.List;

public class Session {
    private String name;
    private List<Exercise> exercises;
    //date???
    //time????

    public Session(String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }
}
