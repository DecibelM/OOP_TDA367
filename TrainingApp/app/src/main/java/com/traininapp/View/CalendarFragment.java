package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.traininapp.Model.Planning.Session;
import com.traininapp.R;
import com.traininapp.viewModel.CalendarViewModel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Responsible for the calendar and everything in it.
 *
 * Author: Adam Törnkvist
 */
public class CalendarFragment extends Fragment {

    private TextView myDate;
    private CalendarViewModel viewModel;
    private ListView listView;
    private List <String> sessionList;
    private LocalDate localDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, null);
        viewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        myDate = view.findViewById(R.id.myDate);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        listView = view.findViewById(R.id.listViewCalendar);
        FloatingActionButton btnOpen = view.findViewById(R.id.btnOpenID);
        localDate = LocalDate.now();
        sessionList = new ArrayList<>() ;
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, sessionList);
        listView.setAdapter(adapter);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d - MM - yyyy");
        myDate.setText(dateFormat.format(date));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               openSession(viewModel.getSession(i, localDate));
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewSession();
            }
        });
        updateSessionList(LocalDate.now());

        // Listener for when the user selects a date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;
                localDate = LocalDate.of(year,month,day);
                String dateString = (day) + " - " + (month) + " - " + year;
                myDate.setText(dateString);
                updateSessionList(localDate);
            }
        });
        return view;
    }

    /**
     * Update the list below the calendar with sessions from the selected day if there are any. Otherwise hides the list
     * @param localDate the currently selected date
     */
    // Method for updating sessions on the selected day
    private void updateSessionList(LocalDate localDate){

        ArrayList<Session> sessionNameList = viewModel.getSessionListByDate(localDate);

        sessionList.clear();
        if(sessionNameList != null) {
            for (Session s : sessionNameList) {
                    sessionList.add(s.getName());
            }
        }
        if (sessionNameList.isEmpty()) {
            listView.setVisibility(View.INVISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
        }
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    /**
     * Opens a completely new Session where you can add exercises and whatnot. Since you have a date selected in the calendar, the date will already be selected in the CreateSessionview
     */
    private void openNewSession(){
        Intent intent = new Intent(getActivity(), CreateSession.class);
        intent.putExtra("DATE", myDate.getText());
        intent.putExtra("FROMCALENDAR", true);
        startActivity(intent);
    }

    /**
     * Opens up an already existing session in the CurrentSessionActivity
     * @param session the session that you want to open
     */
    private void openSession(Session session){
        Intent intent = new Intent(getActivity(), CurrentSessionActivity.class);
        System.out.println(session);
        intent.putExtra("Session", session.toString());
        startActivity(intent);
    }
}
