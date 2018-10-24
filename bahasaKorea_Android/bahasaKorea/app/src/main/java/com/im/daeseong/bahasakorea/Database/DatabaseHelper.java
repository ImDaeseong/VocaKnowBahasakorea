package com.im.daeseong.bahasakorea.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    static final String DATABASE_NAME = "KoreanDB.db";
    static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;

    private static DatabaseHelper instance;
    public static DatabaseHelper getInstance(Context context ) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        try {
            database = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public synchronized void close() {

        if (database != null) {
            database.close();
        }
        super.close();
    }
}
