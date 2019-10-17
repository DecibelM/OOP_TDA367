package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.R;
import com.traininapp.viewModel.CalendarViewModel;
import com.traininapp.viewModel.SelectedSessionViewModel;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectedSessionFragment extends Fragment {

    private List<Exercise> exerciseList = new ArrayList<>();

    private ViewModel viewModel;
    private LocalDate selectedDate;
    private Time time;
    private Button doneBtn;
    private TextView sessionName;
    private TextView sessionDate;
    private TextView sessionTime;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.open_session, null);
        viewModel = ViewModelProviders.of(this).get(SelectedSessionViewModel.class);

        doneBtn = view.findViewById(R.id.btnDoneID);
        sessionName = view.findViewById(R.id.sessionName);
        sessionDate = view.findViewById(R.id.sessionDate);
        sessionTime = view.findViewById(R.id.sessionTime);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionDone();
            }
        });
        return view;
    }

    public void sessionDone(){
        Intent intent = new Intent(getActivity(), UpcomingSessionsViewModel.class);
        startActivity(intent);
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
