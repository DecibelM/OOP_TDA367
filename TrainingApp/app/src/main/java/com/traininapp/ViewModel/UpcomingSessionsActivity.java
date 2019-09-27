package com.traininapp.ViewModel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.Model.Employee;
import com.traininapp.R;

import java.util.ArrayList;

public class UpcomingSessionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private ArrayList<Employee> employeeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeArrayList = new ArrayList<>();
        employeeArrayList.add(new Employee("Employee1", "emp1@example.com", "123456789"));
        employeeArrayList.add(new Employee("Employee2", "emp2@example.com", "987654321"));
        employeeArrayList.add(new Employee("Employee3", "emp3@example.com", "789456123"));
        employeeArrayList.add(new Employee("Employee4", "emp4@example.com", "321654987"));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new EmployeeAdapter(employeeArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingSessionsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}