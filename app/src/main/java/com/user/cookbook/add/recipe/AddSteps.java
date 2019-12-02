package com.user.cookbook.add.recipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.user.cookbook.MainActivity;
import com.user.cookbook.R;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.model.Step;

import java.util.ArrayList;

public class AddSteps extends AppCompatActivity {
    ArrayList<Step> steps;
    Recipe recipe;
    int counter;
    EditText desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_steps);
        counter=1;
        steps = new ArrayList<>();
        recipe = (Recipe)getIntent().getSerializableExtra("recipeToAdd");
        desc = findViewById(R.id.editText2);
        Button button  = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(desc.getText().toString().equals("")){
                    Toast toast = Toast.makeText(AddSteps.this,
                            getString(R.string.empty_values), Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    steps.add(new Step(counter,desc.getText().toString()));
                    counter ++;
                    Toast toast = Toast.makeText(AddSteps.this,
                            getString(R.string.step_add), Toast.LENGTH_SHORT);
                    toast.show();
                    desc.getText().clear();
                }
            }
        });
        Button finish = findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(steps.size()==0){
                    Toast toast = Toast.makeText(AddSteps.this,
                            getString(R.string.no_ingredients), Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    recipe.setSteps(steps);
                    Intent intent = new Intent(AddSteps.this, MainActivity.class);
                    intent.putExtra("recipeToAdd",recipe);
                    startActivity(intent);

                }
            }
        });
    }
    private EditText createNewEditText(){
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        final EditText editText = new EditText(this);
        editText.setLayoutParams(lparams);
        return editText;
    }
}
