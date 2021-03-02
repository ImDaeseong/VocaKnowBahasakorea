package com.daeseong.bahasakorea.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import java.util.*

class KataManager {

    private val tag = KataManager::class.java.simpleName

    private val TABLE_KATAS = "Katas"

    companion object {

        private var databaseHelper: DatabaseHelper? = null

        private var instance: KataManager? = null
        fun getInstance(context: Context): KataManager {
            if (instance == null) {

                databaseHelper = DatabaseHelper(context)
                //databaseHelper = DatabaseHelper.getInstance(context)

                instance = KataManager()
            }
            return instance as KataManager
        }
    }

    fun setKata(items: KataItems) {

        if (databaseHelper == null) {
            return
        }

        val db  = databaseHelper!!.writableDatabase

        try {
            val values = ContentValues()
            values.put("rIndex", items.rIndex)
            values.put("KataKor", items.kataKor)
            values.put("KataIndo", items.kataIndo)
            values.put("KataEng", items.kataEng)
            db !!.insert(TABLE_KATAS, null, values)
        } catch (e: SQLiteException) {
        } catch (e: Exception) {
        } finally {
            db ?.close()
        }
    }

    fun getTotalcount(): Int {

        var nTotalcount = 0

        if (databaseHelper == null) {
            return nTotalcount
        }

        val database = databaseHelper!!.readableDatabase ?: return nTotalcount
        val query = "select count(*) from Katas"
        var cursor = database.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                nTotalcount = cursor.getInt(0)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return nTotalcount
    }

    fun getKata(sSearch: String): KataItems? {

        var items: KataItems? = null

        if (databaseHelper == null) {
            return items
        }

        val database = databaseHelper!!.readableDatabase ?: return items
        val query = "select * from Katas where KataKor  =  \"$sSearch\""
        var cursor = database.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                val KataKor = cursor.getString(cursor.getColumnIndex("KataKor"))
                val KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"))
                val KataEng = cursor.getString(cursor.getColumnIndex("KataEng"))
                items = KataItems(rIndex, KataKor, KataIndo, KataEng)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return items
    }

    fun getKata(nIndex: Int): KataItems? {

        var items: KataItems? = null

        if (databaseHelper == null) {
            return items
        }

        val database = databaseHelper!!.readableDatabase ?: return items
        val query = "select * from Katas where rIndex =$nIndex"
        var cursor = database.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                val KataKor = cursor.getString(cursor.getColumnIndex("KataKor"))
                val KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"))
                val KataEng = cursor.getString(cursor.getColumnIndex("KataEng"))
                items = KataItems(rIndex, KataKor, KataIndo, KataEng)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return items
    }

    fun getKata(): ArrayList<KataItems>? {

        if (databaseHelper == null) {
            return null
        }

        val database = databaseHelper!!.readableDatabase ?: return null
        val list = ArrayList<KataItems>()
        val query = "select * from Katas "
        var cursor = database.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                for (i in 0 until cursor.count) {
                    val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                    val KataKor = cursor.getString(cursor.getColumnIndex("KataKor"))
                    val KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"))
                    val KataEng = cursor.getString(cursor.getColumnIndex("KataEng"))
                    val items = KataItems(rIndex, KataKor, KataIndo, KataEng)
                    list.add(items)
                    cursor.moveToNext()
                }
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return list
    }

    fun getSearchKata(sSearch: String): ArrayList<KataItems>? {

        if (databaseHelper == null) {
            return null
        }

        val database = databaseHelper!!.readableDatabase ?: return null
        val list = ArrayList<KataItems>()
        var cursor: Cursor? = null

        try {

            //KataKor
            var query = "SELECT * from Katas where KataKor LIKE '$sSearch%'"
            cursor = database.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                for (i in 0 until cursor.count) {
                    val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                    val KataKor = cursor.getString(cursor.getColumnIndex("KataKor"))
                    val KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"))
                    val KataEng = cursor.getString(cursor.getColumnIndex("KataEng"))
                    val items = KataItems(rIndex, KataKor, KataIndo, KataEng)
                    list.add(items)
                    cursor.moveToNext()
                }
            }

            //KataIndo
            query = "SELECT * from Katas where KataIndo LIKE '$sSearch%'"
            cursor = database.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                for (i in 0 until cursor.count) {
                    val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                    val KataKor = cursor.getString(cursor.getColumnIndex("KataKor"))
                    val KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"))
                    val KataEng = cursor.getString(cursor.getColumnIndex("KataEng"))
                    val items = KataItems(rIndex, KataKor, KataIndo, KataEng)
                    list.add(items)
                    cursor.moveToNext()
                }
            }

            //KataEng
            query = "SELECT * from Katas where KataEng LIKE '$sSearch%'"
            cursor = database.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                for (i in 0 until cursor.count) {
                    val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                    val KataKor = cursor.getString(cursor.getColumnIndex("KataKor"))
                    val KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"))
                    val KataEng = cursor.getString(cursor.getColumnIndex("KataEng"))
                    val items = KataItems(rIndex, KataKor, KataIndo, KataEng)
                    list.add(items)
                    cursor.moveToNext()
                }
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return list
    }

    fun getHangulTotalcount(): Int {

        var nTotalcount = 0

        if (databaseHelper == null) {
            return nTotalcount
        }

        val database = databaseHelper!!.readableDatabase ?: return nTotalcount
        val query = "select count(*) from Hangul"
        var cursor = database.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                nTotalcount = cursor.getInt(0)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return nTotalcount
    }

    fun getHangul(nIndex: Int): HangulItems? {

        var items: HangulItems? = null

        if (databaseHelper == null) {
            return items
        }

        val database = databaseHelper!!.readableDatabase ?: return items
        val query = "select * from Hangul where rIndex =$nIndex"
        var cursor = database.rawQuery(query, null)

        try {
            if (cursor!!.moveToFirst()) {
                cursor.moveToFirst()
                val rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"))
                val KataFirst = cursor.getString(cursor.getColumnIndex("KataFirst"))
                val KataSecond = cursor.getString(cursor.getColumnIndex("KataSecond"))
                val KataEnd = cursor.getString(cursor.getColumnIndex("KataEnd"))
                items = HangulItems(rIndex, KataFirst, KataSecond, KataEnd)
            }
        } catch (e: SQLiteException) {
        } catch (e: java.lang.Exception) {
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
            database?.close()
        }
        return items
    }

}
