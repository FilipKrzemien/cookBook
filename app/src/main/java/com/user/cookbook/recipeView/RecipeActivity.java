package com.user.cookbook.recipeView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.user.cookbook.R;
import com.user.cookbook.db.model.Ingredient;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.model.Step;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    ArrayList<Step> steps;
    ArrayList<Ingredient> ingredients;
    int current;
    TextView description;
    TextView name;
    TextView difficulty;
    TextView timeNeed;
    TextView ingredientsList;
    Button next;
    Button previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        current = 0;
        Recipe recipe = (Recipe)getIntent().getSerializableExtra("Recipe");
        steps = recipe.getSteps();
        ingredients = recipe.getIngredients();
        getSupportActionBar().hide();
        description = findViewById(R.id.stepDescription);
        description.setText(prepareDescription());
        description.setMovementMethod(new ScrollingMovementMethod());
        next = findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current<steps.size()-1){
                    current++;
                    description.setText(prepareDescription());
                }
            }
        });
        previous = findViewById(R.id.buttonBack);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current>0){
                    current--;
                    description.setText(prepareDescription());
                }
            }
        });
        name = findViewById(R.id.recipeName2);
        name.setText(recipe.getName());
        difficulty = findViewById(R.id.recipeDifficulty2);
        difficulty.setText(recipe.getDifficulty());
        timeNeed = findViewById(R.id.recipeTimeNeed);
        timeNeed.setText(String.valueOf(recipe.getTime()+" min"));
        ingredientsList = findViewById(R.id.ingredientsList);
        ingredientsList.setMovementMethod(new ScrollingMovementMethod());
        ingredientsList.setText(prepareIngredients());
    }

    private String prepareIngredients() {
        StringBuilder textToReturn = new StringBuilder();
        for(Ingredient ingredient : ingredients){
            textToReturn.append(ingredient.getName()).append(" ").
                    append(ingredient.getAmount()).append(" ").
                    append(ingredient.getUnit()).append("\n");
        }
        return textToReturn.toString();
    }

    private String prepareDescription() {
        return String.valueOf(steps.get(current).getStepNumber())
                +". "+steps.get(current).getDescription();
    }


}
