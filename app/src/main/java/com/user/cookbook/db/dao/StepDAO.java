package com.user.cookbook.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.user.cookbook.db.model.Step;
import com.user.cookbook.db.tables.StepTable;

import java.util.ArrayList;

public class StepDAO implements IDAO<Step> {
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    private static final String insert = "INSERT INTO " + StepTable.tableName + " (" +
            StepTable.StepColumns.recipeID + ", " + StepTable.StepColumns.stepNumber + ", " +
            StepTable.StepColumns.description + ") VALUES(?,?,?)";

    public StepDAO(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(StepDAO.insert);
    }

    @Override
    public long save(Step type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, String.valueOf(type.getRecipeId()));
        insertStatement.bindString(2, String.valueOf(type.getStepNumber()));
        insertStatement.bindString(3, type.getDescription());
        return insertStatement.executeInsert();
    }

    @Override
    public ArrayList<Step> getAll() {
        return null;
    }

    public ArrayList<Step> getStepsFromRecipe(int recipeID) {
        String sql = "SELECT " + StepTable.StepColumns.stepNumber + ", "
                + StepTable.StepColumns.description + " FROM " + StepTable.tableName + " " +
                "WHERE " + StepTable.StepColumns.recipeID + "=" + String.valueOf(recipeID);
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Step> steps = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int number = cursor.getInt(0);
                String description = cursor.getString(1);
                steps.add(buildStep(number, description, recipeID));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return steps;
    }

    private Step buildStep(int number, String description, int recipeID) {
        return new Step(recipeID, number, description);
    }

}
