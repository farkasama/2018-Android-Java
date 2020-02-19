package com.example.etien.examexo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD extends SQLiteOpenHelper {
    public static final String BDName = "HIST";
    public static String TABLE = "HISTORY";
    public static String EVENT = "evenement";
    public static String DAY = "jour";
    public static String MONTH = "mois";
    public static String YEAR = "annee";
    private Context context;

    public BD(Context context, int version) {
        super(context, BDName, null, version);
        this.context = context;
    }

    private final static String CREATE_TABLE = "create table " + TABLE + "(" +
            "_id INTEGER primary key autoincrement," +
            EVENT + " text NOT NULL, " +
            DAY + " integer ," +
            MONTH + " integer," +
            YEAR + " integer NOT NULL" +
            ");";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        String [] events = context.getResources().getStringArray(R.array.event);
        int[] year = context.getResources().getIntArray(R.array.annee);
        int[] month = context.getResources().getIntArray(R.array.mois);
        int[] day = context.getResources().getIntArray(R.array.jour);
        for(int i=0; i<events.length; i++){
            ContentValues values = new ContentValues();
            values.put(EVENT, events[i]);
            values.put(YEAR, year[i]);
            values.put(MONTH,month[i]);
            values.put(DAY,day[i]);
            long id = db.insert(TABLE,null,values);
            //Log.d("BD insert " , id+"");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + TABLE);
            onCreate(db);
        }
    }
}
