package com.traininapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.traininapp.MainActivity;
import com.traininapp.Model.Database.GoalTable;
import com.traininapp.R;
import com.traininapp.viewModel.GoalsViewModel;
import java.util.ArrayList;
import java.util.List;

/** Handles the view for adding a goal.
 * Author: Viktor
 */

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
        fillSpinner();

        return view;
    }

    /**
     * fills the spinner with the names of existing exercises
     */
    private void fillSpinner() {
        List<String> exerciseList = new ArrayList<>(viewModel.getStatNames());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, exerciseList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseChoise.setAdapter(spinnerArrayAdapter);
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
        if (exerciseTarget.getText().length()>0 && exerciseChoise.getSelectedItem()!=null) {
        Double target = Double.valueOf(exerciseTarget.getText().toString());
        String activityType = exerciseChoise.getSelectedItem().toString();
        GoalTable goalTable = new GoalTable(getContext());
        goalTable.insertData(activityType, target);
        viewModel.createGoal(activityType, target);
        }
    }

    /**
     * Replaces current fragment with 'this'
     */
    private void replaceFragment() {
        MainActivity mainActivity = (MainActivity) getActivity();
        ((MainActivity) getActivity()).removeFragment(AddGoalFragment.this);
    }
}
