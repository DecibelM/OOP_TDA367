package com.traininapp.View;


import com.traininapp.adapter.IStatistic;

/**
 * This class keeps hold of the goals and the progression towards them
 */
public class GoalStatCard implements IStatistic {

        private String goalName;
        private Double goalTarget;
        private Double goalProgress;

    /**
     * The constructor for the class GoalStatCard
     *
     * @param goalName
     * @param goalTarget
     * @param goalProgress
     */
    GoalStatCard(String goalName, Double goalTarget, Double goalProgress) {
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

    public Double getTarget() {
        return goalTarget;
    }

    public Double getProgress() {
        return goalProgress;
    }
}
