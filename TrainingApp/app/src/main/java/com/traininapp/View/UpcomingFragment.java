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
import com.traininapp.viewModel.CreateSession2;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton btnOpen;
    private SessionAdapter adapter;
    private List<Session> sessionList;
    private View view;
    private FloatingActionButton btnAddSession;

    private UpcomingSessionsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Attaching the View model to activity
        viewModel = ViewModelProviders.of(this).get(UpcomingSessionsViewModel.class);

        // Connecting to fragment_upcoming.xml
        view = inflater.inflate(R.layout.fragment_upcoming, null);

        // Attach listener to "Add session" FAB
        btnAddSession = view.findViewById(R.id.btnAddSessionFAB);
        btnAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddSessionClick();
            }
        });


        // Initializing the list of sessions and add sessions
        sessionList = new ArrayList<>();

        Intent intent = getActivity().getIntent();

        String name = intent.getStringExtra("SELECTED_ROUTINE");
        /*String dateString = intent.getStringExtra("SELECTED_DATE");

        LocalDate date = LocalDate.parse(dateString);*/

        if (name != null){
            viewModel.addSessionToList(name, LocalDate.now());
            intent.removeExtra("SELECTED_ROUTINE");
        }

        updateSessionList();

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


    // Load the sessionList with Sessions saved in the User's Planner
    public void updateSessionList(){

        sessionList.clear();

        for (Session s : viewModel.getListOfSessions()){
            sessionList.add(s);
        }

    }


    /**
     * Directs the user to CreateSession activity when pressing the "Add session" FAB
     */
    public void onAddSessionClick(){

        // Creating and initializing the intent object
        Intent intent = new Intent(getActivity(), CreateSession2.class);

        /*// Attaching the key value pair using putExtra to this intent
        intent.putExtra("VIEW_MODEL", upcomingSessionsViewModel);*/

        // Starting the activity
        startActivity(intent);
    }

}