package com.user.cookbook;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.user.cookbook.db.DataBaseHelper;
import com.user.cookbook.db.dao.IngredientDAO;
import com.user.cookbook.db.dao.RecipeDAO;
import com.user.cookbook.db.dao.StepDAO;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.tables.UnitTable;
import com.user.cookbook.recipeView.RecipeAdapter;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {
    DataBaseHelper dataBaseHelper;
    RecipeDAO recipeDAO;
    IngredientDAO ingredientDAO;
    StepDAO stepDAO;
    SQLiteDatabase db;
    ArrayList<Recipe> recipes;
    RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getDb();
        stepDAO = new StepDAO(db);
        ingredientDAO = new IngredientDAO(db);
        recipeDAO = new RecipeDAO(db, stepDAO, ingredientDAO);
        recipes = recipeDAO.getAll();
        view = findViewById(R.id.recycler);
        RecipeAdapter adapter = new RecipeAdapter(this,recipes);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));
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
