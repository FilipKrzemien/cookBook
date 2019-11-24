package com.user.cookbook.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class RecipeTable {
    public static final String tableName="RECIPES";

    public static class RecipeColumns implements BaseColumns {
        public static final String recipeName="name";
        public static final String difficulty = "difficulty";
        public static final String time="time";
    }
    public static void onCreate(SQLiteDatabase db) {
        String sb = ("CREATE TABLE " + RecipeTable.tableName + " (") +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                RecipeColumns.recipeName + " TEXT, " +
                RecipeColumns.difficulty + " TEXT," +
                RecipeColumns.time + " DOUBLE);";
        db.execSQL(sb);
    }
}
