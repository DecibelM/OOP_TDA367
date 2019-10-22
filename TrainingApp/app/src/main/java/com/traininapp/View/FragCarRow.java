package com.traininapp.View;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.R;
import java.util.ArrayList;
import java.util.List;

public class FragCarRow extends Fragment {

    //Placeholder list for all cardio exercises
    List<String> carExerciseList = new ArrayList<>();

    private EditText txtEnterTime;

    public String getTxtEnterTime() {
        return txtEnterTime.getText().toString();
    }

    public EditText getTxtEnterDistance() {
        return txtEnterDistance;
    }

    public AutoCompleteTextView getAutPickCarEx() {
        return autPickCarEx;
    }

    private EditText txtEnterDistance;
    private AutoCompleteTextView autPickCarEx;
    private Button btnDeleteCar;

    CardioExercise exercise; // Borde det kanske inte vara här? Det är svårt att komma åt den annars

    public FragCarRow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)    {
        View v = inflater.inflate (R.layout.fragment_frag_car_row, container,false);

        autPickCarEx = v.findViewById(R.id.autPickCarExID);
        btnDeleteCar = v.findViewById(R.id.btnDeleteCarID);
        txtEnterTime = v.findViewById(R.id.txtEnterTimeID);
        txtEnterDistance = v.findViewById(R.id.txtEnterDistanceID);

        //add placeholder cardio exercises
        carExerciseList.add("Running");
        carExerciseList.add("Swimming");
        carExerciseList.add("Walking");

        //create and setup adapter
        ArrayAdapter<String> carExerciseListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, carExerciseList);
        autPickCarEx.setAdapter(carExerciseListAdapter);

        btnDeleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyFragment();
            }
        });


        autPickCarEx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                exercise.setName(autPickCarEx.getText().toString());
            }
        });

        txtEnterTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
               exercise.setRunningTime(Double.valueOf(txtEnterTime.getText().toString()));
            }
        });

        txtEnterDistance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                exercise.setRunningTime(Double.valueOf(txtEnterDistance.getText().toString()));
            }
        });





        return v;
    }


    //method for creating the exercise from the inputted information
    public CardioExercise saveInfo(){
        //if the user has not entered an exercise name, return null and tell him to do so
        if(autPickCarEx.length() == 0){
            Toast.makeText(getActivity(), "Add exercisename", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered a distance, return null and tell him to do so
        } else if(txtEnterDistance.length() == 0){
            Toast.makeText(getActivity(), "Add distance", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered a time, return null and tell him to do so
        } else if(txtEnterTime.length() == 0){
            Toast.makeText(getActivity(), "Add time", Toast.LENGTH_SHORT).show();
            return null;
        }else{
            //Take name, time and distance from edittext, convert into their respective types
            String name = autPickCarEx.getText().toString();
            double time = Integer.parseInt(txtEnterDistance.getText().toString());
            double distance = Integer.parseInt(txtEnterTime.getText().toString());

            //If the fragment has been destroyed (and given an invalid value), rename it to be filtered out later
            if(time < 0){
                name = "REMOVE ME";
            }

            //create new cardioExercise
            CardioExercise cardioExercise = new CardioExercise(name, time, distance);

            return cardioExercise;
        }
    }

    //remove selected fragment
    public void destroyFragment(){
        //Tell user which Exercise has been removed
        Toast.makeText(getActivity(), "Exercise: " + autPickCarEx.getText().toString() + " has been removed", Toast.LENGTH_SHORT).show();

        //Set name to 'a', so user is not prompted to enter name
        autPickCarEx.setText("a");
        //Set time to an invalid value, to be filtered out
        txtEnterTime.setText("-1");
        //Set distance to invalid value
        txtEnterDistance.setText("-1");

        //Remove the fragment
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


    public void setCardioValues(CardioExercise exercise, FragCarRow fragCarRow){

        fragCarRow.autPickCarEx.setText(exercise.getName());

        fragCarRow.txtEnterTime.setText(Double.toString(exercise.getRunningTime()));
        fragCarRow.txtEnterDistance.setText(Double.toString(exercise.getDistance()));
    }

    public void setExercise (CardioExercise exercise){
        this.exercise = exercise;
    }


}