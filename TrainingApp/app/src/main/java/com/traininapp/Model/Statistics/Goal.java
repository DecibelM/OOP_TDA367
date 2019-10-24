package com.traininapp.Model.Statistics;

/**
 * Class Goal, holds the information of a goal
 * @authors Maria Fornmark Viktor Fredholm
 */
class Goal implements IGoal {

    private String name;
    private Double target;
    private Double progress;

    Goal(String name, Double target) {
        this.name = name;
        this.target = target;
        progress = 0.0;
    }

    /**
     * Updates the progress towards the goal. this is not a percentage, the int represents the current closest effort towards the goal.
     * @param newProgress the new progress number
     */
    void updateProgress(Double newProgress){
        progress = newProgress;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getTarget() {
        return target;
    }

    @Override
    public Double getProgress() {
        return progress;
    }
}
