package com.traininapp.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.traininapp.Model.Employee;
import com.traininapp.R;

import java.util.ArrayList;

public class UpcomingFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private ArrayList<Employee> employeeArrayList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upcoming, null);

        employeeArrayList = new ArrayList<>();
        employeeArrayList.add(new Employee("Löpning", "3km", "1h"));
        employeeArrayList.add(new Employee("Mage", "4 sets", "5 reps"));
        employeeArrayList.add(new Employee("Biceps", "1 set", "2 reps"));
        employeeArrayList.add(new Employee("Lilltå", "100 sets", "3000 reps"));

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new EmployeeAdapter(employeeArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingFragment.super.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}