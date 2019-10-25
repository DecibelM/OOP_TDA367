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
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The fragment(row) in create and currentsession showing the data of one StrengthExercise
 * Authors: Adam and Isak
 */

public class FragStrRow extends Fragment {

    //TODO Javadoc
    //Placeholder list for all strength exercises
    private List<String> strExerciseList = new ArrayList<>();

    private EditText txtEnterWeight;
    private EditText txtEnterSets ;
    private EditText txtEnterReps;
    private AutoCompleteTextView autPickStrEx;

    public FragStrRow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_frag_str_row, container,false);

        autPickStrEx = view.findViewById(R.id.autPickStrExID);
        Button btnDeletestr = view.findViewById(R.id.btnDeletestrID);
        txtEnterWeight = view.findViewById(R.id.txtEnterWeightID);
        txtEnterSets = view.findViewById(R.id.txtEnterSetsID);
        txtEnterReps = view.findViewById(R.id.txtEntersRepsID);
        autPickStrEx.setText(getTag());

        //add placeholder strength exercises
        strExerciseList.add("Bicep Curl");
        strExerciseList.add("Dumbbell Curl");
        strExerciseList.add("Dumbbell Shoulder Fly");

        //create and setup adapter
        ArrayAdapter<String> strExerciseListAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strExerciseList);
        autPickStrEx.setAdapter(strExerciseListAdapter);

        btnDeletestr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyFragment();
            }
        });

        return view;
    }

    //method for creating the exercise from the inputted information
    StrengthExercise saveInfo(){
        //if the user has not entered an exercise name, return null and tell him to do so
        if(autPickStrEx.length() == 0){
            Toast.makeText(getActivity(), "Add exercisename", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered a weight, return null and tell him to do so
        } else if(txtEnterWeight.length() == 0){
            Toast.makeText(getActivity(), "Add weight", Toast.LENGTH_SHORT).show();
            return null;
            //if the user has not entered reps, return null and tell him to do so
        } else if(txtEnterReps.length() == 0){
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
            int reps = Integer.parseInt(txtEnterReps.getText().toString());
            double weight = Double.parseDouble(txtEnterWeight.getText().toString());

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
    private void destroyFragment(){

        //Remove the fragment
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

        //Tell user which Exercise has been removed
        String toastMessage = "Exercise: " + autPickStrEx.getText().toString() + " has been removed";
        Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_SHORT).show();

        //Set name to 'a', so user is not prompted to enter name
        autPickStrEx.setText("a");
        //Set reps to invalid value
        txtEnterReps.setText("-1");
        //Set weight to invalid value
        txtEnterWeight.setText("-1");
        //Set sets to an invalid value, to be filtered out
        txtEnterSets.setText("-1");

    }

    /**
     * Loads in the values for the fragment from the current exercise
     * @param exercise the current exercise
     */
    void setValues(StrengthExercise exercise){

        DecimalFormat df = new DecimalFormat("###.#");

        txtEnterWeight.setText(String.valueOf(df.format(exercise.getWeight())));
        txtEnterReps.setText(String.valueOf(exercise.getReps()));
        txtEnterSets.setText(String.valueOf(exercise.getSets()));
        autPickStrEx.setText(exercise.getName());
    }

    /**
     * Set the editability for the textview
     * @param edit boolean for if the textviews should be editable or not
     */

    void setEditable(Boolean edit){
            txtEnterWeight.setEnabled(edit);
            txtEnterReps.setEnabled(edit);
            txtEnterSets.setEnabled(edit);
            autPickStrEx.setEnabled(edit);
    }
}