package com.user.cookbook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.user.cookbook.R;

public class StepDescriptionFragment extends Fragment {

    public StepDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step_description, container, false);
        TextView text = v.findViewById(R.id.stepDescription);
        text.setMovementMethod(new ScrollingMovementMethod());
        return v;
    }
}
