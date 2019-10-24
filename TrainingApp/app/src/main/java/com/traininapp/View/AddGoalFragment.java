package com.traininapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.traininapp.R;

public class AddGoalFragment extends Fragment {

    Spinner exerciseChoise;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_goal, container, false);

        initFields(view);

        return view;
    }

    private void initFields(View view) {

    }
    // TODO make this work
}
