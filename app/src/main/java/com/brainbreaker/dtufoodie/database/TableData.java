package com.brainbreaker.dtufoodie.database;

import android.provider.BaseColumns;

/**
 * Created by brainbreaker on 19/8/15.
 */
public class TableData {

    public TableData(){


    }

    public static abstract class TableInfo implements BaseColumns{



        public static final String COLUMN_ID = "id";
        public static final String COLUMN_hostel = "hostel";
        public static final String COLUMN_weekday = "weekday";
        public static final String COLUMN_category = "category";
        public static final String COLUMN_food = "food";
        public static final String COLUMN_rate = "rate";

        public static final String DATABASE_NAME = "DTUFoodie.db";
        public static final String TABLE_NAME= "foodie";


    }
}
