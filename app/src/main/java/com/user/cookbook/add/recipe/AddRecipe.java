package com.user.cookbook.add.recipe;

import android.annotation.SuppressLint;
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
import com.user.cookbook.db.model.Recipe;


public class AddRecipe extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText nameText;
    private EditText timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipe);

        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                nameText = v.getRootView().findViewById(R.id.editText);
                String name = nameText.getText().toString();

                timeText = v.getRootView().findViewById(R.id.editText3);
                String timeString = timeText.getText().toString();
                int time = Integer.parseInt(timeString);

                radioGroup = findViewById(R.id.radioDifficulty);
                int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(selectedRadioButtonID);
                String difficulty = radioButton.getText().toString();

                boolean isFormNull = name.isEmpty() && timeString.isEmpty() && difficulty.isEmpty();

                if(!isFormNull) {
                    Recipe recipe = new Recipe(name, time, difficulty);
                    Intent intent = new Intent(AddRecipe.this, AddIngredients.class);
                    intent.putExtra("recipe", recipe);

                    startActivity(intent);
                } else {
                    Toast.makeText(AddRecipe.this, R.string.empty_form,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



}
