package com.user.cookbook.add.recipe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.user.cookbook.MainActivity;
import com.user.cookbook.R;
import com.user.cookbook.db.dao.RecipeDAO;
import com.user.cookbook.db.dao.UnitDao;
import com.user.cookbook.db.model.Ingredient;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.model.Unit;

import java.util.ArrayList;

public class AddIngredients extends AppCompatActivity {
    Context context;
    TextView header;
    LayoutInflater inflater;
    EditText ingredient_1;
    EditText time;
    View ingredient;
    LinearLayout mainLayout;
    Recipe recipe;
    ArrayList<Ingredient> ingredientArrayList;
    ArrayList<Unit> units;
    Spinner unitDropDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = (Recipe)getIntent().getSerializableExtra("recipeToAdd");
        ingredientArrayList = new ArrayList<>();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_ingredients);
        header = findViewById(R.id.headerTV);
        mainLayout = findViewById(R.id.linearLayout2);
        ingredient = findViewById(R.id.first_ingredient);
        units = (ArrayList<Unit>) getIntent().getSerializableExtra("unitDAO");
        ArrayAdapter<Unit> arrayAdapter = new ArrayAdapter<Unit>(this,android.R.layout.simple_spinner_item,units);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredient_1 = ingredient.findViewById(R.id.ingredient_1_name);
        time = ingredient.findViewById(R.id.editText8);
        unitDropDown = ingredient.findViewById(R.id.spinner);
        unitDropDown.setPrompt("unit");
        unitDropDown.setAdapter(arrayAdapter);
        context = getApplicationContext();
        inflater = this.getLayoutInflater();


        Button button  = findViewById(R.id.addRecipeButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ingredient_1.getText().toString().equals("")
                        || time.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(AddIngredients.this,
                            getString(R.string.empty_values), Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Unit u1 = (Unit) unitDropDown.getSelectedItem();
                    ingredientArrayList.add(new Ingredient(ingredient_1.getText().toString(),
                            Double.valueOf(time.getText().toString()),String.valueOf(u1.getId())));
                    Toast toast = Toast.makeText(AddIngredients.this,
                            getString(R.string.ingredient_add), Toast.LENGTH_SHORT);
                    toast.show();
                    ingredient_1.getText().clear();
                    time.getText().clear();
                }
            }
        });

        Button nextButton = findViewById(R.id.addStepButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ingredientArrayList.size()==0){
                    Toast toast = Toast.makeText(AddIngredients.this,
                            getString(R.string.no_ingredients), Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    recipe.setIngredients(ingredientArrayList);
                    Intent intent = new Intent(AddIngredients.this, AddSteps.class);
                    intent.putExtra("recipeToAdd",recipe);
                    startActivity(intent);

                }
            }
        });
    }


}
