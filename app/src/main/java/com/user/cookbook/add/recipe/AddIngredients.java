package com.user.cookbook.add.recipe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.user.cookbook.R;

public class AddIngredients extends AppCompatActivity {
    Context context;
    TextView header;


    LayoutInflater inflater;
    EditText ingredient_1;
    View ingredient;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_ingredients);
        header = findViewById(R.id.headerTV);

        mainLayout = findViewById(R.id.linearLayout2);
        ingredient = findViewById(R.id.first_ingredient);

        ingredient_1 = ingredient.findViewById(R.id.ingredient_1_name);

        context = getApplicationContext();
        inflater = this.getLayoutInflater();

        setContentView(R.layout.activity_add_ingredients);

        Button button  = findViewById(R.id.addRecipeButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText ingredient_1 = v.getRootView().findViewById(R.id.ingredient_1_name);

                System.out.println(ingredient_1.getText());

                LinearLayout layout =
                        (LinearLayout) inflater.inflate(R.layout.ingredient_fragment,
                                null, false);

                System.out.println(v);
                LinearLayout linear = v.getRootView().findViewById(R.id.linearLayout2);
                linear.addView(layout);
            }
        });

        Button nextButton = findViewById(R.id.addStepButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddIngredients.this, AddSteps.class);
                startActivity(intent);
            }
        });
    }


}
