package com.traininapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private String name;
    private List<Exercise> exercises;
    //date???
    //time????

    public Session(String name) {
        this.name = name;
        this.exercises = new ArrayList<>();
    }
}
