package com.traininapp.viewModel;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.View.FragStrRow;

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
    public CreateSessionViewModel(){
        this.repo = Repository.getInstance();
    }



}
