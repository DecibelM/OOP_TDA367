package com.traininapp.viewModel;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.View.FragStrRow;

import java.time.LocalDate;
import java.util.List;

/**
 * Class CreateSessionViewModel is a class representing the view model for creating CreateSessionActivity view
 */
public class CreateSessionViewModel extends ViewModel {

    // Singleton repo
    private Repository repo;
    private FragmentTransaction fragmentTransaction;

    /**
     * Constructor which fetches the singleton repo
     */
    public CreateSessionViewModel() {
        this.repo = Repository.getInstance();
    }

    public void addSession(String name, List<Exercise> exerciseList, LocalDate date, int image) {
        repo.addSession(name, exerciseList, date, image);
    }

}
