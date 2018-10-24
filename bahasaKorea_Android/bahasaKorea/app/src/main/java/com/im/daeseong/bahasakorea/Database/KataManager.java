package com.im.daeseong.bahasakorea.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

public class KataManager {

    private static final String TAG = KataManager.class.getSimpleName();

    private DatabaseHelper databaseHelper = null;

    private static final String TABLE_KATAS = "Katas";

    private static KataManager instance = null;
    public static KataManager getInstance(Context context) {
        if (instance == null) {
            instance = new KataManager(context);
        }
        return instance;
    }

    private KataManager(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void setKata(KataItems items){

        if(databaseHelper == null){
            return;
        }

        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("rIndex", items.getrIndex());
            values.put("KataKor", items.getKataKor());
            values.put("KataIndo", items.getKataIndo());
            values.put("KataEng", items.getKataEng());
            database.insert("Katas", null, values);

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {
            if(database != null){
                database.close();
            }
        }
    }

    public int getTotalcount(){

        int nTotalcount = 0;

        if(databaseHelper == null){
            return nTotalcount;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return nTotalcount;
        }

        String query = "select count(*) from Katas";
        Cursor cursor = database.rawQuery(query, null);

        try{

            if(cursor.moveToFirst()) {
                cursor.moveToFirst();
                nTotalcount =cursor.getInt(0);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }
        return nTotalcount;
    }

    public KataItems getKata(String sSearch){

        KataItems items = null;

        if(databaseHelper == null){
            return items;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return items;
        }

        String query = "select * from Katas where KataKor " + " =  \"" + sSearch + "\"";
        Cursor cursor = database.rawQuery(query, null);

        try{

            if(cursor.moveToFirst()) {
                cursor.moveToFirst();
                int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                String KataKor = cursor.getString(cursor.getColumnIndex("KataKor"));
                String KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"));
                String KataEng = cursor.getString(cursor.getColumnIndex("KataEng"));
                items = new KataItems(rIndex, KataKor, KataIndo, KataEng);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }
        return items;
    }

    public KataItems getKata(int nIndex){

        KataItems items = null;

        if(databaseHelper == null){
            return items;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return items;
        }

        String query = "select * from Katas where rIndex ="  + nIndex;
        Cursor cursor = database.rawQuery(query, null);

        try{

            if(cursor.moveToFirst()) {
                cursor.moveToFirst();
                int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                String KataKor = cursor.getString(cursor.getColumnIndex("KataKor"));
                String KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"));
                String KataEng = cursor.getString(cursor.getColumnIndex("KataEng"));
                items = new KataItems(rIndex, KataKor, KataIndo, KataEng);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }
        return items;
    }

    public ArrayList<KataItems> getKata(){

        if(databaseHelper == null){
            return null;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return null;
        }

        ArrayList<KataItems> list = new ArrayList<>();

        String query = "select * from Katas ";
        Cursor cursor = database.rawQuery(query, null);

        try{

            if(cursor.moveToFirst()) {

                for (int i=0; i < cursor.getCount(); i++){
                    int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                    String KataKor = cursor.getString(cursor.getColumnIndex("KataKor"));
                    String KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"));
                    String KataEng = cursor.getString(cursor.getColumnIndex("KataEng"));
                    KataItems items = new KataItems(rIndex, KataKor, KataIndo, KataEng);
                    list.add(items);
                    cursor.moveToNext();
                }
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }
        return list;
    }

    public ArrayList<KataItems> getSearchKata(String sSearch){

        if(databaseHelper == null){
            return null;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return null;
        }

        ArrayList<KataItems> list = new ArrayList<>();
        Cursor cursor = null;

        try {

            //KataKor
            String query = "SELECT * from Katas where KataKor LIKE '" + sSearch + "%'";
            cursor = database.rawQuery(query, null);
            if(cursor.moveToFirst()) {

                for (int i=0; i < cursor.getCount(); i++){
                    int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                    String KataKor = cursor.getString(cursor.getColumnIndex("KataKor"));
                    String KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"));
                    String KataEng = cursor.getString(cursor.getColumnIndex("KataEng"));
                    //Log.e(TAG, "KataKor:" + KataKor);
                    KataItems items = new KataItems(rIndex, KataKor, KataIndo, KataEng);
                    list.add(items);
                    cursor.moveToNext();
                }
            }

            //KataIndo
            query = "SELECT * from Katas where KataIndo LIKE '" + sSearch + "%'";
            cursor = database.rawQuery(query, null);
            if(cursor.moveToFirst()) {

                for (int i=0; i < cursor.getCount(); i++){
                    int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                    String KataKor = cursor.getString(cursor.getColumnIndex("KataKor"));
                    String KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"));
                    String KataEng = cursor.getString(cursor.getColumnIndex("KataEng"));
                    //Log.e(TAG, "KataKor:" + KataKor);
                    KataItems items = new KataItems(rIndex, KataKor, KataIndo, KataEng);
                    list.add(items);
                    cursor.moveToNext();
                }
            }

            //KataEng
            query = "SELECT * from Katas where KataEng LIKE '" + sSearch + "%'";
            cursor = database.rawQuery(query, null);
            if(cursor.moveToFirst()) {

                for (int i=0; i < cursor.getCount(); i++){
                    int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                    String KataKor = cursor.getString(cursor.getColumnIndex("KataKor"));
                    String KataIndo = cursor.getString(cursor.getColumnIndex("KataIndo"));
                    String KataEng = cursor.getString(cursor.getColumnIndex("KataEng"));
                    //Log.e(TAG, "KataKor:" + KataKor);
                    KataItems items = new KataItems(rIndex, KataKor, KataIndo, KataEng);
                    list.add(items);
                    cursor.moveToNext();
                }
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }

        return list;
    }

    public int getHangulTotalcount(){

        int nTotalcount = 0;

        if(databaseHelper == null){
            return nTotalcount;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return nTotalcount;
        }

        String query = "select count(*) from Hangul";
        Cursor cursor = database.rawQuery(query, null);

        try{

            if(cursor.moveToFirst()) {
                cursor.moveToFirst();
                nTotalcount =cursor.getInt(0);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }
        return nTotalcount;
    }

    public HangulItems getHangul(int nIndex){

        HangulItems items = null;

        if(databaseHelper == null){
            return items;
        }

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if(database == null){
            return items;
        }

        String query = "select * from Hangul where rIndex ="  + nIndex;
        Cursor cursor = database.rawQuery(query, null);

        try{

            if(cursor.moveToFirst()) {
                cursor.moveToFirst();
                int rIndex = cursor.getInt(cursor.getColumnIndex("rIndex"));
                String KataFirst = cursor.getString(cursor.getColumnIndex("KataFirst"));
                String KataSecond = cursor.getString(cursor.getColumnIndex("KataSecond"));
                String KataEnd = cursor.getString(cursor.getColumnIndex("KataEnd"));
                items = new HangulItems(rIndex, KataFirst, KataSecond, KataEnd);
            }

        } catch (SQLiteException e){
        } catch (Exception e){
        } finally {

            if(cursor != null){
                cursor.close();
                cursor = null;
            }

            if(database != null){
                database.close();
            }
        }
        return items;
    }

}
