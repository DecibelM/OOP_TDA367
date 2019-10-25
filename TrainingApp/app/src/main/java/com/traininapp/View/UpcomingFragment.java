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
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.traininapp.R;
import com.traininapp.adapter.SessionAdapter;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

/**
 * The fragment containing the view of the upcoming Sessions
 * @author Mathias
 */
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

        TextView emptyView = view.findViewById(R.id.empty_view);

        // Specifying the adapter on a sorted list of session gotten from the v
        SessionAdapter adapter = new SessionAdapter(viewModel.getSortedSessionList());
        recyclerView.setAdapter(adapter);

        //If sessionList is empty, a textview is shown instead
        if (viewModel.getSortedSessionList().isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.INVISIBLE);
        }

        // Using a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingFragment.super.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    /**
     * Directs the user to CreateSessionActivity activity when pressing the "Add session" FAB
     */
    private void onAddSessionClick(){

        // Creating and initializing the intent object
        Intent intent = new Intent(getActivity(), CreateSessionActivity.class);

        // Starting the activity
        startActivity(intent);
    }
}