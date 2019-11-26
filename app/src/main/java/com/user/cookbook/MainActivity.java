package com.user.cookbook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.user.cookbook.add.recepie.AddRecipe;
import com.user.cookbook.db.DataBaseHelper;
import com.user.cookbook.db.dao.IngredientDAO;
import com.user.cookbook.db.dao.RecipeDAO;
import com.user.cookbook.db.dao.StepDAO;
import com.user.cookbook.db.model.Recipe;

public class MainActivity extends AppCompatActivity {
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
        db=dataBaseHelper.getDb();
        stepDAO = new StepDAO(db);
        ingredientDAO = new IngredientDAO(db);
        recipeDAO = new RecipeDAO(db, stepDAO, ingredientDAO);
        Button button1 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddRecipe.class);
                view.getContext().startActivity(intent);}
        });
    }
}
