package com.traininapp.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.traininapp.R;

public class CalendarFragment extends Fragment {

    private TextView myDate;
    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setCalendarView();
        //setMyDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = (day) + "/" + month + "/" + year;
                myDate.setText(date);
            }
        });




        return inflater.inflate(R.layout.fragment_calendar, null);

    }
    /*
    public void setCalendarView(){
        calendarView = (CalendarView) findViewById(R.id.calendarView);
    }

    public void setMyDate(){
        myDate = (TextView) findViewById(R.id.myDate);
    }*/








}
