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
import android.widget.TextView;

import com.traininapp.R;
import com.traininapp.adapter.StatisticsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps hold of the goals page or my page. Also holds the recyclerview along with its adapter and Layout manager.
 */
public class GoalsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> listExample;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.myPagesRecyclerViewID);
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new StatisticsAdapter(listExample);

        layoutManager= new LinearLayoutManager(GoalsFragment.super.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

}


