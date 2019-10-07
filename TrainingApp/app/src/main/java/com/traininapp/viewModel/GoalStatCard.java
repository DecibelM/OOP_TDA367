package com.traininapp.viewModel;

import com.traininapp.Model.Goal;


/**
* This class keeps hold of the goals and the progression toeards them
*
*
*/

public class GoalStatCard implements IStatistic {

        String goal;

    /**
     * @param goal the goal
     */
    public GoalStatCard(String goal) {
        this.goal = goal;
    }

    @Override
        public int getType() {
            return IStatistic.TYPE_GOALSTAT;
        }

    public String getGoal() {
        return goal;
    }

}
