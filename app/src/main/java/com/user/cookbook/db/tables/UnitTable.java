package com.user.cookbook.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class UnitTable {
    public static final String tableName="UNITS";

    public static class UnitColumns implements BaseColumns {
        public static final String unitName = "name";
    }
    public static void onCreate(SQLiteDatabase db) {
        String sb = ("CREATE TABLE "+UnitTable.tableName +" (" +
                BaseColumns._ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                UnitColumns.unitName +" TEXT);");
        db.execSQL(sb);
    }
    }
