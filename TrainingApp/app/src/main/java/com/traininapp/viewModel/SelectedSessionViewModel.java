package com.traininapp.viewModel;

import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.Model.Planning.Session;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class CalendarViewModel is a class representing the viewmodel for the calendar.
 */
public class SelectedSessionViewModel extends ViewModel {
    private Repository model;

    public SelectedSessionViewModel() {
        this.model = Repository.getInstance();
    }


}