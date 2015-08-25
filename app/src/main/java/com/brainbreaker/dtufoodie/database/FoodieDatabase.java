package com.brainbreaker.dtufoodie.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by brainbreaker on 18/8/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FoodieDatabase extends SQLiteOpenHelper {

   public String DATABASE_CREATE = "create table "
           + TableData.TableInfo.TABLE_NAME + "("
           + TableData.TableInfo.COLUMN_ID + " text primary key ,"
           + TableData.TableInfo.COLUMN_hostel + " text not null,"
           + TableData.TableInfo.COLUMN_weekday + " text not null,"
           + TableData.TableInfo.COLUMN_category + " text not null,"
           + TableData.TableInfo.COLUMN_food + " text not null,"
           + TableData.TableInfo.COLUMN_rate + " text not null" + ")";


    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    public FoodieDatabase(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.w("FoodieDatabase","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
                database.execSQL(DATABASE_CREATE);
        Log.w(FoodieDatabase.class.getName(),
                " Table " + TableData.TableInfo.TABLE_NAME + " Successfully created. "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertvalues(FoodieDatabase fdb, String id, String hostel,
                             String weekday, String category, String food , String rate ){
        SQLiteDatabase sdb = fdb.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.COLUMN_ID,id);
        cv.put(TableData.TableInfo.COLUMN_hostel,hostel );
        cv.put(TableData.TableInfo.COLUMN_weekday,weekday );
        cv.put(TableData.TableInfo.COLUMN_category,category );
        cv.put(TableData.TableInfo.COLUMN_food,food );
        cv.put(TableData.TableInfo.COLUMN_rate,rate );

        sdb.insert(TableData.TableInfo.TABLE_NAME, null, cv);
    }

    public Cursor retrievevalues(FoodieDatabase fdb){

        SQLiteDatabase sq = fdb.getReadableDatabase();
        String[] columns = {TableData.TableInfo.COLUMN_ID,
                TableData.TableInfo.COLUMN_hostel,
                TableData.TableInfo.COLUMN_weekday,
                TableData.TableInfo.COLUMN_category,
                TableData.TableInfo.COLUMN_food,
                TableData.TableInfo.COLUMN_rate};
        Cursor CR = sq.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }

}