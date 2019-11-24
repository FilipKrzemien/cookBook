package com.user.cookbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.user.cookbook.db.tables.IngredientTable;
import com.user.cookbook.db.tables.RecipeTable;
import com.user.cookbook.db.tables.StepTable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dataBaseName="CookBookDB";
    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        return db;
    }

    public DataBaseHelper(Context context) {
        super(context, dataBaseName, null, 1);
        this.db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        RecipeTable.onCreate(db);
        StepTable.onCreate(db);
        IngredientTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
