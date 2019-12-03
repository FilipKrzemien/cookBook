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
import com.user.cookbook.db.dao.UnitDao;
import com.user.cookbook.db.model.Recipe;
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
//
//        db.execSQL("delete from "+ IngredientTable.tableName);
//        db.execSQL("delete from "+ StepTable.tableName);
//        db.execSQL("delete from "+ RecipeTable.tableName);

        Unit u1= new Unit("elo");
        unitDAO.save(u1);

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

    @Override
    protected void onResume() {
        super.onResume();
        Recipe r = (Recipe) getIntent().getSerializableExtra("recipeToAdd");
        if(r!=null){
          recipeDAO.insertRecipe(r);
            recipes = recipeDAO.getAll();
            view = findViewById(R.id.recycler);
            RecipeAdapter adapter = new RecipeAdapter(this,recipes);
            view.setAdapter(adapter);
            view.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public ArrayList<Unit> getUnitList() {
        return unitDAO.getAll();
    }
}
