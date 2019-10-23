package com.traininapp.View;


import com.traininapp.adapter.IStatistic;

/**
* This class keeps hold of the goals and the progression toeards them
*
*
*/

public class GoalStatCard implements IStatistic {

        //TODO Javadoc. Gör variabler private
        String goalName;
        Double goalTarget;
        Double goalProgress;

    /**
     * The constructor for the class GoalStatCard
     *  @param goalName
     * @param goalTarget
     * @param goalProgress
     */
    public GoalStatCard(String goalName, Double goalTarget, Double goalProgress) {
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
