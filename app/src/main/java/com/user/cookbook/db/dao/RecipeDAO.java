package com.user.cookbook.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.user.cookbook.db.model.Ingredient;
import com.user.cookbook.db.model.Recipe;
import com.user.cookbook.db.model.Step;
import com.user.cookbook.db.tables.RecipeTable;

import java.util.ArrayList;

public class RecipeDAO implements IDAO<Recipe> {
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    private StepDAO stepDAO;
    private IngredientDAO ingredientDAO;
    private static final String insert="INSERT INTO " + RecipeTable.tableName +" ("+
            RecipeTable.RecipeColumns.recipeName +", "+RecipeTable.RecipeColumns.difficulty+", "+
            RecipeTable.RecipeColumns.time +") values (?, ?, ?)";

    public RecipeDAO(SQLiteDatabase db,StepDAO stepDAO, IngredientDAO ingredientDAO) {
        this.db = db;
        this.stepDAO=stepDAO;
        this.ingredientDAO=ingredientDAO;
        insertStatement = db.compileStatement(RecipeDAO.insert);
    }

    @Override
    public long save(Recipe type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1,type.getName());
        insertStatement.bindString(2,type.getDifficulty());
        insertStatement.bindString(3,String.valueOf(type.getTime()));
        return insertStatement.executeInsert();
    }

    @Override
    public ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT "+ RecipeTable.RecipeColumns._ID +", "+ RecipeTable.RecipeColumns.recipeName +", " +
                RecipeTable.RecipeColumns.difficulty + ", " +
                RecipeTable.RecipeColumns.time +"FROM " + RecipeTable.tableName;
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String difficulty = cursor.getString(2);
                double time = cursor.getDouble(3);
                ArrayList<Step> steps = stepDAO.getStepsFromRecipe(id);
                ArrayList<Ingredient> ingredients = ingredientDAO.getIngredientsFromRecipe(id);
                recipes.add(buildRecipe(id, name, difficulty, time, steps, ingredients));
                cursor.moveToNext();
            }

        }
        cursor.close();
        return recipes;
    }

    private Recipe buildRecipe(int id, String name, String difficulty, double time,
                               ArrayList<Step> steps, ArrayList<Ingredient> ingredients) {
        Recipe recipe = new Recipe(name,time,difficulty,id);
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        return recipe;
    }
}
