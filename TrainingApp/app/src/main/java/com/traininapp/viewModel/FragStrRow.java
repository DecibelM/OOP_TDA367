package com.traininapp.viewModel;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.traininapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragStrRow extends Fragment {

    //List for all strength exercises
    List<String> strExerciseList = new ArrayList<>();

    public FragStrRow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_frag_str_row, container,false);

        AutoCompleteTextView autPickStrEx = v.findViewById(R.id.autPickStrExID);
        Button btnDeletestr = v.findViewById(R.id.btnDeletestrID);

        //add all strength activites
        strExerciseList.add("Bicep Curl");
        strExerciseList.add("Dumbbell Curl");
        strExerciseList.add("Dumbbell Shoulder Fly");

        //set up adapter and AutoCompleteTextview
        ArrayAdapter<String> strExerciseListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strExerciseList);
        autPickStrEx.setAdapter(strExerciseListAdapter);

        btnDeletestr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyFragment();
            }
        });

        return v;
    }

    //remove selected fragment
    public void destroyFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

}