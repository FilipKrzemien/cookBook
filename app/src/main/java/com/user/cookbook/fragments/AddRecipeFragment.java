package com.user.cookbook.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.user.cookbook.R;
import com.user.cookbook.add.recipe.AddRecipe;

public class AddRecipeFragment extends Fragment implements View.OnClickListener {

    Button addButton;

    public AddRecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        addButton = myView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        return myView;
    }


    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(getActivity(), AddRecipe.class);
        startActivity(myIntent);
    }
}
