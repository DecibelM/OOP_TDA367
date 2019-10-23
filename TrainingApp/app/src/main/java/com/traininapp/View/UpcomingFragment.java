package com.traininapp.View;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.traininapp.Model.Planning.Session;
import com.traininapp.R;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import java.util.List;

public class UpcomingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Attaching the View model to activity
        UpcomingSessionsViewModel viewModel = ViewModelProviders.of(this).get(UpcomingSessionsViewModel.class);

        // Connecting to fragment_upcoming.xml
        View view = inflater.inflate(R.layout.fragment_upcoming, null);

        // Attach listener to "Add session" FAB
        FloatingActionButton btnAddSession = view.findViewById(R.id.btnAddSessionFAB);
        btnAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddSessionClick();
            }
        });

        // Changes in content does not change layout size, set
        // to true for improved performance
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openSession(); Kör när du kan klicka på korten
            }
        });

        // Getting a sorted list of Sessions from Planner's list of Sessions, based on date
        List<Session> sortedSessionList = viewModel.getSortedSessionList();

        // Specifying the adapter
        SessionAdapter adapter = new SessionAdapter(sortedSessionList);
        recyclerView.setAdapter(adapter);

        // Using a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingFragment.super.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    /**
     * Directs the user to CreateSession activity when pressing the "Add session" FAB
     */
    private void onAddSessionClick(){

        // Creating and initializing the intent object
        Intent intent = new Intent(getActivity(), CreateSession.class);

        // Starting the activity
        startActivity(intent);
    }


    public void openSession() {
        Intent intent = new Intent(getActivity(), CurrentSessionActivity.class);
    }


    public void openSession(Session session){
        Intent intent = new Intent(getActivity(), CurrentSessionActivity.class);
        intent.putExtra("Session", session.toString());

        startActivity(intent);
    }

}