package com.traininapp.Model.Statistics;

public interface IGoal {
    /**
     * @return the name of this goal.
     */
    String getName();

    /**
     * @return the target of this specific goal.
     */
    Double getTarget();

    /**
     * @return current best effort towards the goal.
     */
    Double getProgress();
}
