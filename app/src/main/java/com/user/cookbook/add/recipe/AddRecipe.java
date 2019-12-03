package com.user.cookbook.add.recipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.user.cookbook.R;
import com.user.cookbook.db.dao.RecipeDAO;
import com.user.cookbook.db.dao.UnitDao;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.model.Unit;

import java.util.ArrayList;


public class AddRecipe extends AppCompatActivity {
    EditText name;
    EditText time;
    RadioGroup difficulty;
    ArrayList<Unit> units;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipe);
        name = findViewById(R.id.editText);
        getSupportActionBar().hide();
        time = findViewById(R.id.editText3);
        difficulty = findViewById(R.id.radioDifficulty);
        units = (ArrayList<Unit>) getIntent().getSerializableExtra("unitDAO");
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || time.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(AddRecipe.this, getString(R.string.empty_values), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    int radioId = difficulty.getCheckedRadioButtonId();
                    RadioButton button = findViewById(radioId);
                    Recipe recipe = new Recipe(name.getText().toString(),
                            Double.valueOf(time.getText().toString())
                            ,button.getText().toString());
                    Intent intent = new Intent(AddRecipe.this, AddIngredients.class);
                    intent.putExtra("recipeToAdd",recipe);
                    intent.putExtra("unitDAO",units);
                    startActivity(intent);

                }
            }
        });

    }



}
