package com.user.cookbook.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.user.cookbook.db.model.Ingredient;
import com.user.cookbook.db.tables.IngredientTable;
import com.user.cookbook.db.tables.UnitTable;

import java.util.ArrayList;

public class IngredientDAO implements IDAO<Ingredient> {
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    private static final String insert = "INSERT INTO " + IngredientTable.tableName + " (" +
            IngredientTable.IngredientColumns.ingredientName + ", " +
            IngredientTable.IngredientColumns.recipeID + ", " +
            IngredientTable.IngredientColumns.amount + ", " +
            IngredientTable.IngredientColumns.unitID +
            ") VALUES(?,?,?,?)";

    public IngredientDAO(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(IngredientDAO.insert);

    }

    @Override
    public long save(Ingredient type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getName());
        insertStatement.bindString(2, String.valueOf(type.getRecipeID()));
        insertStatement.bindString(3, String.valueOf(type.getAmount()));
        insertStatement.bindString(4, String.valueOf(type.getUnit()));
        return insertStatement.executeInsert();
    }

    @Override
    public ArrayList<Ingredient> getAll() {
        return null;
    }

    public ArrayList<Ingredient> getIngredientsFromRecipe(int recipeID) {
        String sql = "SELECT " + IngredientTable.tableName + "." + IngredientTable.IngredientColumns.ingredientName + ", " +
                IngredientTable.tableName + "." + IngredientTable.IngredientColumns.amount + ", " +
                UnitTable.tableName + "." + UnitTable.UnitColumns.unitName +
                IngredientTable.tableName + "." + IngredientTable.IngredientColumns._ID +" FROM " +
                IngredientTable.tableName + ", " +
                UnitTable.tableName +
                " WHERE " + IngredientTable.IngredientColumns.recipeID + "=" +
                String.valueOf(recipeID) +
                " AND " + IngredientTable.IngredientColumns.unitID + "=" +
                UnitTable.tableName + "." + UnitTable.UnitColumns._ID;
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(0);
                double amount = cursor.getDouble(1);
                String unit = cursor.getString(2);
                long id = cursor.getLong(3);
                ingredients.add(buildIngredient(name, recipeID, amount, unit, id));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return ingredients;
    }

    private Ingredient buildIngredient(String name, int recipeID, double amount, String unit, long id) {
        Ingredient ingredient = new Ingredient(recipeID, name, amount, unit, id);
        return ingredient;
    }
}
