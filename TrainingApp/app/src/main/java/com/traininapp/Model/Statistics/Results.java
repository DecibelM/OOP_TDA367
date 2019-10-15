package com.traininapp.Model.Statistics;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private List<Goal> goalList;
    private Statistic statistic;

    public Results() {
        this.goalList = new ArrayList<>();
        this.statistic = new Statistic();
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void addGoal(String name, int target){
        Goal goal = new Goal(name, target);
        goalList.add(goal);
    }
}
