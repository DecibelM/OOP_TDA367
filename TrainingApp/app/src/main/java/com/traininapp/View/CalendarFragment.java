package com.traininapp.View;

import android.content.Context;
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

import com.traininapp.MainActivity;
import com.traininapp.R;
import com.traininapp.viewModel.CalendarViewModel;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, null);
        viewModel = ((MainActivity)getActivity()).getCvm();
        myDate = (TextView) view.findViewById(R.id.myDate);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        listView = (ListView) view.findViewById(R.id.listViewCalendar);

        list = new ArrayList<>() ;
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        myDate.setText(dateFormat.format(date));

        final Context context = this.getContext();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;
                String date = (day) + "-" + (month) + "-" + year;
                myDate.setText(date);
                ArrayList<String> newList = viewModel.getSessionsByDate(LocalDate.of(year, month, day));
                list.clear();
                if(newList != null) {
                    for (String s : newList) {
                        list.add(s);
                    }
                }
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

            }
        });
        return view;
    }
}
