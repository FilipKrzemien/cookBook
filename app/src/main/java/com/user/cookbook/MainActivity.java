package com.user.cookbook;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.user.cookbook.db.DataBaseHelper;
import com.user.cookbook.db.dao.IngredientDAO;
import com.user.cookbook.db.dao.RecipeDAO;
import com.user.cookbook.db.dao.StepDAO;


public class MainActivity extends FragmentActivity {
    DataBaseHelper dataBaseHelper;
    RecipeDAO recipeDAO;
    IngredientDAO ingredientDAO;
    StepDAO stepDAO;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getDb();
        stepDAO = new StepDAO(db);
        ingredientDAO = new IngredientDAO(db);
        recipeDAO = new RecipeDAO(db, stepDAO, ingredientDAO);

        Switch languageSwitchBtn = findViewById(R.id.switchLanguage);
        final TextView switchBtn_txtView = findViewById(R.id.languageSwitchBtn_txtView);
        switchBtn_txtView.setText(getResources().getString(R.string.polish_language));

        languageSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchBtn_txtView.setText(getResources().getString(R.string.english_language));
                }
                else {
                    switchBtn_txtView.setText(getResources().getString(R.string.polish_language));
                }
            }
        });






    }
}
