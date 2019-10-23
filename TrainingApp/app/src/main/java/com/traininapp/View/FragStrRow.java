package com.traininapp.View;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.traininapp.Model.Planning.Exercise;

import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragStrRow extends Fragment {

    //Placeholder list for all strength exercises
    private List<String> strExerciseList = new ArrayList<>();


    private EditText txtEnterWeight;
    private EditText txtEnterSets ;
    private EditText txtEntersReps;
    private AutoCompleteTextView autPickStrEx;
    private StrengthExercise exercise;

    private FragmentTransaction fragmentTransaction;

    public FragStrRow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_frag_str_row, container,false);

        autPickStrEx = v.findViewById(R.id.autPickStrExID);
        Button btnDeletestr = v.findViewById(R.id.btnDeletestrID);
        txtEnterWeight = v.findViewById(R.id.txtEnterWeightID);
        txtEnterSets = v.findViewById(R.id.txtEnterSetsID);
        txtEntersReps = v.findViewById(R.id.txtEntersRepsID);

        autPickStrEx.setText(getTag());

        //add placeholder strength exercises
        strExerciseList.add("Bicep Curl");
        strExerciseList.add("Dumbbell Curl");
        strExerciseList.add("Dumbbell Shoulder Fly");


        //create and setup adapter
        ArrayAdapter<String> strExerciseListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strExerciseList);
        autPickStrEx.setAdapter(strExerciseListAdapter);

        autPickStrEx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                exercise.setName(autPickStrEx.getText().toString());
            }
        });

        txtEnterWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                exercise.setWeight(Double.valueOf(txtEnterWeight.getText().toString()));
            }
        });

        txtEnterSets.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                exercise.setSets(Integer.valueOf(txtEnterSets.getText().toString()));
            }
        });

        txtEntersReps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                exercise.setReps(Integer.valueOf(txtEntersReps.getText().toString()));
            }
        });

        btnDeletestr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyFragment();
            }
        });

        return v;
    }

    //method for creating the exercise from the inputted information
    public StrengthExercise saveInfo(){
        //if the user has not entered an exercise name, return null and tell him to do so
        if(autPickStrEx.length() == 0){
            Toast.makeText(getActivity(), "Add exercisename", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered a weight, return null and tell him to do so
        } else if(txtEnterWeight.length() == 0){
            Toast.makeText(getActivity(), "Add weight", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered reps, return null and tell him to do so
        } else if(txtEntersReps.length() == 0){
            Toast.makeText(getActivity(), "Add reps", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered sets, return null and tell him to do so
        } else if(txtEnterSets.length() == 0){
            Toast.makeText(getActivity(), "Add sets", Toast.LENGTH_SHORT).show();
            return null;
        }else{
            //Take name, sets, reps and weight from edittext, convert into their respective types
            String name = autPickStrEx.getText().toString();
            int sets = Integer.parseInt(txtEnterSets.getText().toString());
            int reps = Integer.parseInt(txtEntersReps.getText().toString());
            double weight = Integer.parseInt(txtEnterWeight.getText().toString());

            //If the fragment has been destroyed (and given an invalid value), rename it to be filtered out later
            if(sets < 0){
                name = "REMOVE ME";
            }

            //create new strengthExercise
            StrengthExercise strengthExercise = new StrengthExercise(name, sets, reps, weight);

            return strengthExercise;
        }
    }

    //remove selected fragment
    public void destroyFragment(){
        //Tell user which Exercise has been removed
        Toast.makeText(getActivity(), "Exercise: " + autPickStrEx.getText().toString() + " has been removed", Toast.LENGTH_SHORT).show();

        //Set name to 'a', so user is not prompted to enter name
        autPickStrEx.setText("a");
        //Set reps to invalid value
        txtEntersReps.setText("-1");
        //Set weight to invalid value
        txtEnterWeight.setText("-1");
        //Set sets to an invalid value, to be filtered out
        txtEnterSets.setText("-1");

        //Remove the fragment
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void setStrengthValues(StrengthExercise exercise, FragStrRow fragStrRow){

        autPickStrEx.setText(exercise.getName());
        txtEnterSets.setText(String.valueOf(exercise.getSets()));
        txtEntersReps.setText(String.valueOf(exercise.getReps()));
        txtEnterWeight.setText(String.valueOf(exercise.getWeight()));

    }

    public void setExercise(Exercise exercise){

        this.exercise = (StrengthExercise) exercise;
    }
}