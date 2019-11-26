package com.user.cookbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AddIngredients extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        Button button  = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater;
                inflater = LayoutInflater.from(context);
                LinearLayout layout =
                        (LinearLayout) inflater.inflate(R.layout.ingredient_fragment,
                                null, false);

                LinearLayout linear = (LinearLayout)findViewById(R.id.linearLayout2);
                linear.addView(layout);
                linear.invalidate();
            }
        });
    }


}
