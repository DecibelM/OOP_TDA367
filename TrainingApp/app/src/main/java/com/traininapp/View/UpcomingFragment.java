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

import com.traininapp.Model.Session;
import com.traininapp.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {

    private RecyclerView recyclerView;
    private SessionAdapter adapter;
    private List<Session> sessionList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Connecting to fragment_upcoming.xml
        view = inflater.inflate(R.layout.fragment_upcoming, null);

        // Initializing the list of sessions and add sessions
        sessionList = new ArrayList<>();
        createSessionList();

        // Changes in content does not change layout size, set
        // to true for improved performance
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // Specifying the adapter
        adapter = new SessionAdapter(sessionList);
        recyclerView.setAdapter(adapter);

        // Using a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingFragment.super.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    public void createSessionList(){
        sessionList.add(new Session("Stronglifts", LocalDate.now(), R.drawable.workout_1));
        sessionList.add(new Session("Yoga", LocalDate.of(2019,3,3), R.drawable.workout_2));
        sessionList.add(new Session("Bicepspass", LocalDate.of(2018,2,5),R.drawable.workout_3));
    }

}