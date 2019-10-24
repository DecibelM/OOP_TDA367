package com.traininapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.traininapp.R;

public class AddGoalFragment extends Fragment {

    private Spinner exerciseChoise;
    private EditText exerciseTarget;
    private Button saveBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_goal, container, false);

        initFields(view);
        initListeners();

        return view;
    }


    private void initFields(View view) {
        exerciseChoise = view.findViewById(R.id.exerciseSpinnerID);
        exerciseTarget = view.findViewById(R.id.txtTargetFieldID);
        saveBtn = view.findViewById(R.id.btnSaveID);
    }
    // TODO make this work
    private void initListeners() {

    }
}
