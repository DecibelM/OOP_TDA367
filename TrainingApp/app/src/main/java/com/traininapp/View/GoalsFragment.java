package com.traininapp.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.traininapp.MainActivity;
import com.traininapp.Model.Repository;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;
import com.traininapp.R;
import com.traininapp.adapter.StatisticsAdapter;
import com.traininapp.adapter.IStatistic;
import com.traininapp.viewModel.GoalsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps hold of the goals page or my page. Also holds the recyclerview along with its adapter and Layout manager.
 * Auth: Viktor Fredholm
 */
public class GoalsFragment extends Fragment {

    //private Repository repository;
    private List<IStatistic> statisticsList;
    private GoalsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, null);
        viewModel = new GoalsViewModel();
        //repository = Repository.getInstance();
        statisticsList = new ArrayList<>();

        bindView(view);
        return view;
    }

    /**
     * Connects the right view to the right element and init them correspondingly.
     * Sets up the RecyclerView and gives it something to use and fill itself with
     */
    private void bindView(View view) {
        Button addGoal = view.findViewById(R.id.btnAddGoalID);

        setupRecyclerView(view);

        for (IStat statistic: viewModel.getStatList()){
            statisticsList.add(new StatisticCard(statistic.getName(), statistic.getDataList(), statistic.getDatesList()));
        }
        for (IGoal goal: viewModel.getGoalList()){
            statisticsList.add(new GoalStatCard(goal.getName(), goal.getTarget(), goal.getProgress()));
        }

        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment();
            }
        });
    }

    /**
     * sets up the recyclerView
     * @param view
     */
    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.myPagesRecyclerViewID);
        recyclerView.setHasFixedSize(true);
        StatisticsAdapter recyclerViewAdapter = new StatisticsAdapter(statisticsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GoalsFragment.super.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    /**
     * replaces the current fragment with 'this'
     */
    private void replaceFragment(){
        MainActivity mainActivity = (MainActivity) getActivity();
        ((MainActivity) getActivity()).replaceFragments(this);
    }
}


