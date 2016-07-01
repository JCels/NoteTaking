package com.example.jasmiensofiecels.notetaking;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;


/**
 * Created by jasmiensofiecels on 29/06/16.
 */
public class notesProvider extends ContentProvider {
    // Authority : globally unique string that identifies the content provider to the Android framework
    private static final String AUTHORITY = "com.example.plainolnotes.notesprovider";
    // Base path : represents the entire data set; this app will only hvae one table
    private static final String BASE_PATH = "notes";
    //Content uri : uniform resource identifier that identifies the content provider
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );


    // Constant to identify the requested operation
    //Notes: "give me the data"
    private static final int NOTES = 1;
    //Notes id: will deal with only a single record
    private static final int NOTES_ID = 2;

    //URIMatcher; NO_MATCH = -1 (constant value)
    private static final UriMatcher uriMatcher = new UriMatcher((UriMatcher.NO_MATCH));

    public static final String CONTENT_ITEM_TYPE = "NOTE";

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, NOTES);
        //# is a wildcard character
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", NOTES_ID);
    }

    private SQLiteDatabase database;
    @Override
    public boolean onCreate() {
        //Creating an instance of OpenHelper class,
        DBOpenHelper helper = new DBOpenHelper(getContext());

        //Create the database
        database = helper.getWritableDatabase();

        //To test, run new emulator; Android Device Monitor > Data >data (sub folder) > com.example_ > database > notes

        return true;
    }

    @Nullable
    @Override
    //will get data from the db
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        if (uriMatcher.match(uri) == NOTES_ID){
            s = DBOpenHelper.NOTE_ID + "=" + uri.getLastPathSegment() ;

        }
        return database.query(DBOpenHelper.TABLE_NOTES, DBOpenHelper.ALL_Columns, s, strings1, null, null, DBOpenHelper.NOTE_CREATED + " DESC");

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    //uri needs to match pattern provided by uriMatcher
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = database.insert(DBOpenHelper.TABLE_NOTES, null, contentValues);
        return Uri.parse(BASE_PATH + " /" + id );
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return database.delete(DBOpenHelper.TABLE_NOTES, s , strings);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return database.update(DBOpenHelper.TABLE_NOTES, contentValues, s , strings);
    }
}
