package com.user.cookbook.recipeView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.user.cookbook.R;
import com.user.cookbook.db.model.Recipe;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Recipe> recipes;

    public RecipeAdapter(Context context, ArrayList<Recipe> reciepes) {
        this.context = context;
        this.recipes = reciepes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.recipeName.setText(recipes.get(i).getName());
        viewHolder.recipeDifficulty.setText(recipes.get(i).getDifficulty());

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe r = recipes.get(i);
                Intent intent = new Intent(context,RecipeActivity.class);
                intent.putExtra("Recipe",r);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    CircleImageView image;
    TextView recipeName;
    TextView recipeDifficulty;
    RelativeLayout layout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        recipeName = itemView.findViewById(R.id.recipeName);
        recipeDifficulty = itemView.findViewById(R.id.recipeDifficulty);
        layout = itemView.findViewById(R.id.parent_layout);

    }
}
}