package com.daeseong.bahasakorea.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val tag = DatabaseHelper::class.java.simpleName

        //데이타베이스 버전
        private const val DATABASE_VERSION = 1

        //데이타베이스 이름
        private const val DATABASE_NAME = "KoreanDB.db"

        private var database: SQLiteDatabase? = null

        private var instance: DatabaseHelper? = null
        fun getInstance(context: Context): DatabaseHelper? {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }
            return instance
        }
    }

    init {

        try {
            database = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(),null, SQLiteDatabase.OPEN_READWRITE)
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        //Log.e(tag, "onCreate")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //Log.e(tag, "onUpgrade")
    }

    @Synchronized
    override fun close() {
        if (database != null) {
            database!!.close()
        }
        super.close()
    }
}