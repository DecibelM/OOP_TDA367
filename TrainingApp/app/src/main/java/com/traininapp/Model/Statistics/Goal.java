package com.traininapp.Model.Statistics;

class Goal implements IGoal {

    String name;
    Double target;
    Double progress;

    public Goal(String name, Double target) {
        this.name = name;
        this.target = target;
        progress = 0.0;
    }

    /**
     * Updates the progress towards the goal. this is not a percentage, the int represents the current closest effort towards the goal.
     * @param newProgress the new progress number
     */
    public void updateProgress(Double newProgress){
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
