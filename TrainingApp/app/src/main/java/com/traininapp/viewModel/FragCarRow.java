package com.traininapp.viewModel;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.traininapp.R;
import java.util.ArrayList;
import java.util.List;

public class FragCarRow extends Fragment {

    //List for all cardio exercises
    List<String> carExerciseList = new ArrayList<>();

    public FragCarRow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)    {
        View v = inflater.inflate (R.layout.fragment_frag_car_row, container,false);

        AutoCompleteTextView auto = v.findViewById(R.id.autPickCarExID);
        Button btnDeleteCar = v.findViewById(R.id.btnDeleteCarID);

        //add all cardio exercises
        carExerciseList.add("Running");
        carExerciseList.add("Swimming");
        carExerciseList.add("Walking");

        //set up adapter and AutoCompleteTextview
        ArrayAdapter<String> carExerciseListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, carExerciseList);
        auto.setAdapter(carExerciseListAdapter);

        btnDeleteCar.setOnClickListener(new View.OnClickListener() {
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
