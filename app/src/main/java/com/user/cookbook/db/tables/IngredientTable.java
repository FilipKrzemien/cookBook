package com.user.cookbook.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class IngredientTable {
    public static final String tableName="INGREDIENTS";

    public static class IngredientColumns implements BaseColumns {
        public static final String ingredientName = "name";
        public static final String recipeID = "recipeID";
        public static final String amount = "amount";
        public static final String unitID = "unitID";
    }

    public static void onCreate(SQLiteDatabase db) {
        String sb = ("CREATE TABLE " +IngredientTable.tableName + " (") +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                IngredientColumns.ingredientName + " TEXT, " +
                IngredientColumns.recipeID +" INTEGER, "+
                IngredientColumns.amount +" DOUBLE, "+
                IngredientColumns.unitID +" INTEGER);";
        db.execSQL(sb);
    }
}
