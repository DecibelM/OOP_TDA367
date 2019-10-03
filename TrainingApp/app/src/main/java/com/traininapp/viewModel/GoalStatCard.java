package com.traininapp.viewModel;

import com.traininapp.Model.Goal;

public class GoalStatCard implements IStatistic {

        String goal;
        String progress;

    public GoalStatCard(String goal, String progress) {
        this.goal = goal;
        this.progress = progress;
    }

    @Override
        public int getType() {
            return IStatistic.TYPE_GOALSTAT;
        }

    public String getGoal() {
        return goal;
    }

    public String getProgress() {
        return progress;
    }
}
