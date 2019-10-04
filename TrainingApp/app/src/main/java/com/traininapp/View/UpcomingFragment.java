package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.traininapp.R;
import com.traininapp.viewModel.CreateSession;
import com.traininapp.viewModel.PickDate;

public class UpcomingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_upcoming, null);
        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);

        ImageButton imgbtn = v.findViewById(R.id.ibtnID);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();

            }
        });

        return v;

    }

    public void openActivity(){
        Intent intent = new Intent(getActivity(), PickDate.class);

        startActivity(intent);
    }
}