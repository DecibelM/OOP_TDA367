package com.traininapp.Model;

import java.util.List;

public class Calendar {

    private String name;
    private List<Session> sessionList;

    public Calendar(String name, List<Session> sessionList) {
        this.name = name;
        this.sessionList = sessionList;
    }

}
