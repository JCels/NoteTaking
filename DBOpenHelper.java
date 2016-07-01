package com.example.jasmiensofiecels.notetaking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jasmiensofiecels on 29/06/16.
 */

//class defines the db structure and creates the db in persistent storage

public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants taken from git.oi/jxTM
    private static final String DATABASE_NAME = "notes.db"; //must include .db
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_NOTES = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TEXT = "noteText";
    public static final String NOTE_CREATED = "noteCreated";

    //In order for the query method to get access to the db columns
    public static final String[] ALL_Columns = {NOTE_ID, NOTE_TEXT,NOTE_CREATED};

    //SQL to create table; will be accessed through content provider, thus must be _ID
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTES + " (" +
                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTE_TEXT + " TEXT, " +
                    NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";


    //Constructor; import Context
    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //Is called the first time the class is instantiated
    // results in creating the db structure
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //first drop the table, the call onCreate method to recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}

