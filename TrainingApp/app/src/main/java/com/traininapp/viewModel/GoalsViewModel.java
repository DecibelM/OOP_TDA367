package com.traininapp.viewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;

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
}
