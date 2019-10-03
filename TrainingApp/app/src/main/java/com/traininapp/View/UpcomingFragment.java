package com.traininapp.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.traininapp.Model.Session;
import com.traininapp.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {

    private RecyclerView recyclerView;
    private SessionAdapter adapter;
    private List<Session> employeeArrayList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upcoming, null);

        employeeArrayList = new ArrayList<>();
        createSessionList();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new SessionAdapter(employeeArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingFragment.super.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void createSessionList(){
        employeeArrayList.add(new Session("Löpning", LocalDate.now()));
        employeeArrayList.add(new Session("Mage", LocalDate.of(2019,3,3)));
        employeeArrayList.add(new Session("Biceps", LocalDate.of(2018,2,5)));
    }

}