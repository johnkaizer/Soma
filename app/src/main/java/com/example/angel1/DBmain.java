package com.example.angel1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DBNAME= "application.db";
    public static final String TABLENAME = "details";
    public static final int VER = 1;
    public DBmain(@Nullable Context context) {
        super(context, DBNAME,null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+TABLENAME+" (id integer primary key, stdName text,appTitle text, stdEmail text, stdSchool text, stdFee text, stdParents text, stdOrphan text, stdDisabilities text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = " drop table if exists "+TABLENAME+"";
        db.execSQL(query);

    }
    public Cursor viewData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLENAME+" WHERE  stdParents = 'None works' AND stdOrphan= 'Yes' AND stdDisabilities = 'Yes' AND stdFee <='10000' ", null);

        return cursor;
    }
}
