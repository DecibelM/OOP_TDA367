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

import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.adapter.StatisticsAdapter;
import com.traininapp.viewModel.GoalStatCard;
import com.traininapp.viewModel.IStatistic;
import com.traininapp.viewModel.StatisticCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Keeps hold of the goals page or my page. Also holds the recyclerview along with its adapter and Layout manager.
 */
public class GoalsFragment extends Fragment {

    private RecyclerView recyclerView;
    private StatisticsAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private Repository repository;
    private List<IStatistic> statisticsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goals, null);
        repository = Repository.getInstance();

        statisticsList = new ArrayList<>();

        //TODO move dummy variables to backend and create a way to get it all from here                                                                                         //dummy variables
        ArrayList<Integer> exampleStatList= new ArrayList<>();                                   //dummy variables to test the functionality
        exampleStatList.add(1); exampleStatList.add(3); exampleStatList.add(2); exampleStatList.add(4); exampleStatList.add(5); exampleStatList.add(5);                                    //dummy variables to test the functionality

        ArrayList<Long> exampleDateList = new ArrayList<>();
        exampleDateList.add(new Date(2012, 3, 5).getTime());                  //dummy variables to test the functionality
        exampleDateList.add(new Date(2019,4,5).getTime());                                          //dummy variables to test the functionality
        exampleDateList.add(new Date(2019, 5, 5).getTime());                  //dummy variables to test the functionality
        exampleDateList.add(new Date(2019,5,9).getTime());                                          //dummy variables to test the functionality
        exampleDateList.add(new Date(2019, 5, 10).getTime());                  //dummy variables to test the functionality
        exampleDateList.add(new Date(2019,5,12).getTime());               //dummy variables to test the functionality


        statisticsList.add(new StatisticCard("Strength", exampleStatList, exampleDateList));

        statisticsList.add(new GoalStatCard("biceeps", 60, 30));
        statisticsList.add(new GoalStatCard("ben", 95, 71));
        statisticsList.add(new GoalStatCard("Vandra häck", 600, 350));//TODO fix



        bindView();

        return view;
    }

    /**
     * Connects the right view to the right element and init them correspondingly.
     */
    private void bindView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.myPagesRecyclerViewID);
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new StatisticsAdapter(statisticsList);

        layoutManager= new LinearLayoutManager(GoalsFragment.super.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}


