package com.traininapp.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ListAdapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.traininapp.MainActivity;
import com.traininapp.R;
import com.traininapp.viewModel.CalendarViewModel;
import com.traininapp.viewModel.PickDate;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


/**
 * Keeps tabs on the calendar and the date which the user has selected.
 *
 */
public class CalendarFragment extends Fragment {

    private TextView myDate;
    private CalendarView calendarView;
    private CalendarViewModel viewModel;
    private ListView listView;
    private ArrayList<String> list;
    private ListView emptyView;
    private FloatingActionButton btnOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, null);
        viewModel = ((MainActivity)getActivity()).getCvm();
        myDate = (TextView) view.findViewById(R.id.myDate);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        listView = (ListView) view.findViewById(R.id.listViewCalendar);
        emptyView = (ListView) view.findViewById(R.id.emptyViewCalendar);
        btnOpen = view.findViewById(R.id.btnOpenID);

        list = new ArrayList<>() ;
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d - MM - yyyy");
        myDate.setText(dateFormat.format(date));


        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSession();
            }
        });

        updateSessionList(LocalDate.now());
        final Context context = this.getContext();

        // Listener for when the user selects a date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;
                LocalDate localDate = LocalDate.of(year,month,day);
                String dateString = (day) + " - " + (month) + " - " + year;
                myDate.setText(dateString);

                updateSessionList(localDate);

            }
        });

        return view;
    }

    // Method for updating sessions on the selected day
    public void updateSessionList(LocalDate localDate){

        ArrayList<String> newList = viewModel.getSessionsByDate(localDate);



        list.clear();
        if(newList != null) {
            for (String s : newList) {
                list.add(s);
            }
        }
        if (newList.isEmpty()) {
        listView.setEmptyView(emptyView);
        }
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();



    }

    public void openSession(){
        Intent intent = new Intent(getActivity(), PickDate.class);
        intent.putExtra("Date", myDate.getText());
        startActivity(intent);
    }

}
