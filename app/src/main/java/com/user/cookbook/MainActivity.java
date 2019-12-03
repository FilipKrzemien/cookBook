package com.user.cookbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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
import com.user.cookbook.db.model.Unit;
import com.user.cookbook.recipeView.RecipeAdapter;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends FragmentActivity {
    DataBaseHelper dataBaseHelper;
    RecipeDAO recipeDAO;
    IngredientDAO ingredientDAO;
    StepDAO stepDAO;
    UnitDao unitDAO;
    SQLiteDatabase db;
    ArrayList<Recipe> recipes;
    RecyclerView view;
    Switch languageSwitchBtn;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String LANG="lang";
    public static final String SWITCH="switch";
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


        recipes = recipeDAO.getAll();
        view = findViewById(R.id.recycler);
        RecipeAdapter adapter = new RecipeAdapter(this,recipes);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));
        languageSwitchBtn = findViewById(R.id.switchLanguage);
        final TextView switchBtn_txtView = findViewById(R.id.languageSwitchBtn_txtView);
        switchBtn_txtView.setText(getResources().getString(R.string.polish_language));


        languageSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchBtn_txtView.setText(getResources().getString(R.string.english_language));
                    saveData();
                    setLocale("en");
                    Button b1 = findViewById(R.id.addButton);
                    b1.setText(R.string.add_new_recipe);
                }
                else {
                    switchBtn_txtView.setText(getResources().getString(R.string.polish_language));
                    saveData();
                    setLocale("pl");
                    Button b1 = findViewById(R.id.addButton);
                    b1.setText(R.string.add_new_recipe);
                }
            }
        });

        loadData();
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

    public void setLocale(String localeCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }
    public ArrayList<Unit> getUnitList() {
        return unitDAO.getAll();
    }

    public void saveData(){
        String lang;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH, languageSwitchBtn.isChecked());
        if(languageSwitchBtn.isChecked()){
            lang = "en";
        }
        else {
            lang ="pl";
        }
        editor.putString(LANG,lang);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        boolean switchState = sharedPreferences.getBoolean(SWITCH,false);
        languageSwitchBtn.setChecked(switchState);
        String lang = sharedPreferences.getString(LANG,"");
        setLocale(lang);
    }
}
