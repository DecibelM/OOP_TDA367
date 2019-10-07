package com.traininapp.viewModel;

import com.traininapp.Model.Goal;


/**
* This class keeps hold of the goals and the progression toeards them
*
*
*/

public class GoalStatCard implements IStatistic {

        String goal;
        String progression;
    /**
     * @param goal the goal
     */
    public GoalStatCard(String goal, String progression) {
        this.goal = goal;
        this.progression = progression;
    }

    @Override
        public int getType() {
            return IStatistic.TYPE_GOALSTAT;
        }

    public String getGoal() {
        return goal;
    }

}
