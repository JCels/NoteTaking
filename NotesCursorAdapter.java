package com.example.jasmiensofiecels.notetaking;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jasmiensofiecels on 01/07/16.
 */
public class NotesCursorAdapter extends CursorAdapter {


    public NotesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.note_list_item, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String noteText = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NOTE_TEXT));

        int position = noteText.indexOf(10);
        if (position != -1) {
            noteText = noteText.substring(0, position) + " ...";
            TextView tv = (TextView) view.findViewById(R.id.tvNote);
            tv.setText(noteText);
        }

    }
}


