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

import com.traininapp.MainActivity;
import com.traininapp.R;
import com.traininapp.viewModel.GoalsViewModel;
public class AddGoalFragment extends Fragment {

    private Spinner exerciseChoise;
    private EditText exerciseTarget;
    private Button saveBtn;
    private GoalsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_goal, container, false);
        viewModel = new GoalsViewModel();

        initFields(view);
        initListeners();
        preFillFields();

        return view;
    }

    /**
     * pre fills the fields for the user to ease the user experience
     */
    private void preFillFields() {
        
    }

    /**
     * initiates the fields for easy use
     * @param view
     */
    private void initFields(View view) {
        exerciseChoise = view.findViewById(R.id.exerciseSpinnerID);
        exerciseTarget = view.findViewById(R.id.txtTargetFieldID);
        saveBtn = view.findViewById(R.id.btnSaveID);
    }

    private void initListeners() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                replaceFragment();
            }
        });
    }

    /**
     * Saves the filled data
     */
    private void saveData() {
    }

    /**
     * Replaces current fragment with 'this'
     */
    private void replaceFragment() {
        MainActivity mainActivity = (MainActivity) getActivity();
        ((MainActivity) getActivity()).removeFragment(AddGoalFragment.this);
    }
}
