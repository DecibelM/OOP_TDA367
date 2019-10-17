package com.traininapp.viewModel;


import java.util.List;

/**
* This class keeps hold of the goals and the progression toeards them
*
*
*/

public class GoalStatCard implements IStatistic {

        String goalName;
        Integer goalTarget;
        Integer goalProgress;

    /**
     * The constructor for the class GoalStatCard
     *
     * @param goalName
     * @param goalTarget
     * @param goalProgress
     */
    public GoalStatCard(String goalName, Integer goalTarget, Integer goalProgress) {
        this.goalName = goalName;
        this.goalTarget = goalTarget;
        this.goalProgress = goalProgress;
    }

    @Override
        public int getType() {
            return IStatistic.TYPE_GOALSTAT;
        }

    public String getName() {
        return goalName;
    }

    public Integer getTarget() {
        return goalTarget;
    }

    public Integer getProgress() {
        return goalProgress;
    }
}
