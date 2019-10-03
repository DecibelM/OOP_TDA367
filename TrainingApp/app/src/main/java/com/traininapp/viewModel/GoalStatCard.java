package com.traininapp.viewModel;

import com.traininapp.Model.Goal;

public class GoalStatCard implements IStatistic {

        Goal goal;

    public GoalStatCard(Goal goal) {
        this.goal = goal;
    }

    @Override
        public int getType() {
            return IStatistic.TYPE_GOALSTAT;
        }

    public Goal getGoal() {
        return goal;
    }
}
