package com.traininapp.viewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the viewModel for Goals, this connects the goal related views to the model.
 * Auth: Viktor Fredholm
 */
public class GoalsViewModel {

    private Repository repository;

    public GoalsViewModel(){
        repository = Repository.getInstance();

    }

    public List<IStat> getStatList() {
        return repository.getStatList();
    }


    public List<IGoal> getGoalList() {
        return repository.getGoalList();
    }

    /**
     * This method gets a list of sessions from the model and returns only a list of the names
     * @return a list of names for the available statistics
     */
    public List<String> getStatNames() {
        List<String> names = new ArrayList<>();
        for (IStat stat: getStatList()){
            names.add(stat.getName());
        }
        return names;
    }

    /**
     * Creates a goal
     *
     * @param name Name of goal
     * @param target Target of goal
     */
    public void createGoal(String name, Double target) {
        repository.createGoal(name, target);
    }
}
