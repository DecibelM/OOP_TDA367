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
import com.traininapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FragCarRow extends Fragment {

    // TODO Javadoc. Ta bort saker som inte används. Metoder körs redan, kan ta bort. CardioExercise gör private. REMOVE SPACE! Gör viewmodel till den här och Strength rox.
    // TODO  Gör saker till package private. Döp om Viewen v till View. Ta bort onödig import

    //Placeholder list for all cardio exercises
    List<String> carExerciseList = new ArrayList<>();

    private EditText txtEnterTime;



    private EditText txtEnterDistance;
    private AutoCompleteTextView autPickCarEx;


    public FragCarRow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)    {
        View view = inflater.inflate (R.layout.fragment_frag_car_row, container,false);

        autPickCarEx = view.findViewById(R.id.autPickCarExID);
        Button btnDeleteCar = view.findViewById(R.id.btnDeleteCarID);
        txtEnterTime = view.findViewById(R.id.txtEnterTimeID);
        txtEnterDistance = view.findViewById(R.id.txtEnterDistanceID);

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

        return view;
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
            double time = Double.parseDouble(txtEnterTime.getText().toString());
            double distance = Double.parseDouble(txtEnterDistance.getText().toString());

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

    public void setTxtEnterTime(String string) {
        txtEnterTime.setText(string);
    }

    public void setTxtEnterDistance(String string) {
        txtEnterDistance.setText(string);
    }

    public void setAutPickCarEx(String string) {
        autPickCarEx.setText(string);
    }

    public void setValues(CardioExercise exercise){

        DecimalFormat df = new DecimalFormat("###.#");


        txtEnterTime.setText(String.valueOf(df.format(exercise.getRunningTime())));
        txtEnterDistance.setText(String.valueOf(df.format(exercise.getDistance())));
        autPickCarEx.setText(exercise.getName());

    }
}