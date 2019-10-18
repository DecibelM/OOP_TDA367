package com.traininapp.Model.Statistics;

public interface IGoal {

    /**
     * @return the name of this goal.
     */
    public String getName();

    /**
     * @return the target of this specific goal.
     */
    public Double getTarget();

    /**
     * @return current best effort towards the goal.
     */
    public Double getProgress();
}
