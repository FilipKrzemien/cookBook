package com.user.cookbook.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.user.cookbook.R;

public class ViewRecipesFragment extends Fragment implements View.OnClickListener{

    Button viewButton;

    public ViewRecipesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_view_recipes, container, false);
        viewButton = myView.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View view) {
        //to be modified
        /*Intent myIntent = new Intent(getActivity(), ViewRecipes.class);
        startActivity(myIntent);*/
    }
}
