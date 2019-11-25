package com.user.cookbook.db.tables;

import android.database.sqlite.SQLiteDatabase;

public class StepTable {
    public static final String tableName="STEPS";

    public static class StepColumns {
        public static final String recipeID = "recipeID";
        public static final String stepNumber = "stepNumber";
        public static final String description = "description";
    }
    public static void onCreate(SQLiteDatabase db) {
        String sb = ("CREATE TABLE "+StepTable.tableName +" (" +
                StepColumns.recipeID + " INTEGER, " +
                StepColumns.stepNumber +" INTEGER, "+
                StepColumns.description +" TEXT);");
        db.execSQL(sb);
    }
}
