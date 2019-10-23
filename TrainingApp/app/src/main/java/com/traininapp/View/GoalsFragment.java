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

import com.traininapp.Model.Repository;
import com.traininapp.Model.Statistics.IGoal;
import com.traininapp.Model.Statistics.IStat;
import com.traininapp.R;
import com.traininapp.adapter.StatisticsAdapter;
import com.traininapp.adapter.IStatistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Keeps hold of the goals page or my page. Also holds the recyclerview along with its adapter and Layout manager.
 */
public class GoalsFragment extends Fragment {

    //TODO gör variabler lokala. SPACE! Kommentarer i bindview. Onödig casting. Onödig import
    private RecyclerView recyclerView;
    private StatisticsAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private Repository repository;
    private List<IStatistic> statisticsList;
    private Button addGoal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goals, null);
        repository = Repository.getInstance();
        statisticsList = new ArrayList<>();

        bindView();

        return view;
    }

    /**
     * Connects the right view to the right element and init them correspondingly.
     */
    private void bindView() {
        addGoal = (Button) view.findViewById(R.id.btnAddGoalID);

        recyclerView = (RecyclerView) view.findViewById(R.id.myPagesRecyclerViewID);
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new StatisticsAdapter(statisticsList);

        layoutManager = new LinearLayoutManager(GoalsFragment.super.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);



        for (IStat statistic: repository.getStatList()){
            statisticsList.add(new StatisticCard(statistic.getName(), statistic.getDataList(), statistic.getDatesList()));
        }
        for (IGoal goal: repository.getGoalList()){
            statisticsList.add(new GoalStatCard(goal.getName(), goal.getTarget(), goal.getProgress()));
        }
    }

    private void replaceFragment(Fragment fragment){
    }
}


