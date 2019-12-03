package com.user.cookbook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.user.cookbook.R;

public class TimerButtonFragment extends Fragment implements View.OnClickListener{

    Button timerButton;

    public TimerButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_timer_button, container, false);
        timerButton = myView.findViewById(R.id.timerButton);
        timerButton.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View view) {
        TimerFragment timerFragment= new TimerFragment();
        timerFragment.show(getFragmentManager(),"timerDialog");
    }
}
