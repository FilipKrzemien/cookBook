package com.user.cookbook;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;



public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch simpleSwitchBtn = findViewById(R.id.switchLanguage);
        final TextView switchBtn_txtView = findViewById(R.id.languageSwitchBtn_txtView);
        switchBtn_txtView.setText("Polski");

        simpleSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchBtn_txtView.setText("English");
                }
                else {
                    switchBtn_txtView.setText("Polski");
                }
            }
        });

    }
}
