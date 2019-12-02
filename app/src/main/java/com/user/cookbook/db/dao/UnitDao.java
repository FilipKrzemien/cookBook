package com.user.cookbook.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.user.cookbook.db.model.Unit;
import com.user.cookbook.db.tables.UnitTable;

import java.io.Serializable;
import java.util.ArrayList;

public class UnitDao implements IDAO<Unit>, Serializable {
    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;
    private static final String insert = "INSERT INTO " + UnitTable.tableName + " (" +
            UnitTable.UnitColumns.unitName + ") VALUES(?)";

    public UnitDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(UnitDao.insert);
    }

    @Override
    public long save(Unit type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getName());
        return insertStatement.executeInsert();
    }

    @Override
    public ArrayList<Unit> getAll() {
        String sql="SELECT * FROM "+ UnitTable.tableName;
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Unit> units = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                String Name = cursor.getString(1);
                units.add(buildUnit(id, Name));
                cursor.moveToNext();
            }

        }
        cursor.close();
        return units;
    }

    private Unit buildUnit(long id, String name) {
        return new Unit(id, name);
    }
}
