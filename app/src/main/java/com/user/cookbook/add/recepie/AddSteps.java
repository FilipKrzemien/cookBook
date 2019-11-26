package com.user.cookbook.add.recepie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.user.cookbook.R;

public class AddSteps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_steps);

        Intent intent = getIntent();

        Button button  = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LinearLayout layout = v.getRootView().findViewById(R.id.step_layout);
                layout.addView(createNewEditText());
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
