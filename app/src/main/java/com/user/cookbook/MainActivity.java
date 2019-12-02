package com.user.cookbook;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.user.cookbook.db.DataBaseHelper;
import com.user.cookbook.db.dao.IngredientDAO;
import com.user.cookbook.db.dao.RecipeDAO;
import com.user.cookbook.db.dao.StepDAO;
import com.user.cookbook.db.dao.UnitDao;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.model.Step;
import com.user.cookbook.db.model.Unit;
import com.user.cookbook.db.tables.IngredientTable;
import com.user.cookbook.db.tables.RecipeTable;
import com.user.cookbook.db.tables.StepTable;
import com.user.cookbook.recipeView.RecipeAdapter;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {
    DataBaseHelper dataBaseHelper;
    RecipeDAO recipeDAO;
    IngredientDAO ingredientDAO;
    StepDAO stepDAO;
    UnitDao unitDAO;
    SQLiteDatabase db;
    ArrayList<Recipe> recipes;
    RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getDb();
        unitDAO = new UnitDao(db);
        stepDAO = new StepDAO(db);
        ingredientDAO = new IngredientDAO(db);
        recipeDAO = new RecipeDAO(db, stepDAO, ingredientDAO);

//        Recipe r1 = new Recipe("Zupa z trupa",200,"łatwe");
//        Step s1 = new Step(1,"We wody wlej do gara");
//        Step s2 = new Step(2,"podsmaz sol");
//        Step s3 = new Step(3,"dodaj trupa");
//        Step s4 = new Step(4,"gotuj se ziom");
//        ArrayList<Step> steps = new ArrayList<>();
//        steps.add(s1);
//        steps.add(s2);
//        steps.add(s3);
//        steps.add(s4);
//        r1.setSteps(steps);
//        recipeDAO.insertRecipe(r1);

//        db.execSQL("delete from "+ IngredientTable.tableName);
//        db.execSQL("delete from "+ StepTable.tableName);
//        db.execSQL("delete from "+ RecipeTable.tableName);


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
