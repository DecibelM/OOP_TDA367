package com.traininapp.viewModel;


import java.util.List;

/**
* This class keeps hold of the goals and the progression toeards them
*
*
*/

public class GoalStatCard implements IStatistic {

        List<String> goalNames;
        List<Integer> goalStat;
    /**
     * @param goal the goal
     */
    public GoalStatCard(List<String> goalNames, List<Integer> goalStat) {
        this.goalNames = goalNames;
        this.goalStat = goalStat;
    }

    @Override
        public int getType() {
            return IStatistic.TYPE_GOALSTAT;
        }

    public List<String> getGoals() {
        return goalNames;
    }

}
