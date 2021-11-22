package com.example.datatest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ClassGroup.db";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
            TableInfo.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
            TableInfo.COLUMN_NAME_NAME + " TEXT," +
            TableInfo.COLUMN_NAME_AGE + " INTEGER," +
            TableInfo.COLUMN_NAME_DEP + " TEXT)";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;


    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
