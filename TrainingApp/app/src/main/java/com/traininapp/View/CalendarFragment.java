package com.traininapp.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.traininapp.R;

import java.util.ArrayList;


/**
 * Keeps tabs on the calendar and the date which the user has selected.
 *
 */
public class CalendarFragment extends Fragment {

    private TextView myDate;
    private CalendarView calendarView;
    private ListView listView;
    private String[] list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, null);
        myDate = (TextView) view.findViewById(R.id.myDate);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        listView = (ListView) view.findViewById(R.id.listViewCalendar);

        final Context context = this.getContext();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = (day) + "/" + month + "/" + year;
                myDate.setText(date);
                list[0] = "Löpning";
                list[1] = "Yoga";
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

            }
        });

        list = new String[]{"hej", "hjhej"} ;

        listView = (ListView) view.findViewById(R.id.listViewCalendar);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        return view;

    }









}
