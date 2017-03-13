package com.example.movil.taller_5.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.movil.taller_5.Model.DatabaseHandler;
import com.example.movil.taller_5.Model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by movil on 3/13/17.
 */

public class DataEntryDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler mDbHelper;
    private Context mContext;
    private String[] mAllColumns = { DatabaseHandler.KEY_ID,
            DatabaseHandler.KEY_TITLE, DatabaseHandler.KEY_CONTENT, DatabaseHandler.KEY_DATE };


    public DataEntryDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DatabaseHandler(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Adding new entry
    public long addDataEntry(Note entry) {

        Long index;


        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_TITLE, entry.getTitle());
        values.put(DatabaseHandler.KEY_CONTENT, entry.getContent());
        values.put(DatabaseHandler.KEY_DATE, entry.getDate());

        // Inserting Row
        index = mDatabase.insert(DatabaseHandler.TABLE, null, values);

        return index;
    }

    // Getting single entry
    public Note geDataEntry(int id) {

        Cursor cursor = mDatabase.query(
                DatabaseHandler.TABLE,
                new String[] { DatabaseHandler.KEY_ID, DatabaseHandler.KEY_TITLE, DatabaseHandler.KEY_CONTENT, DatabaseHandler.KEY_DATE},
                DatabaseHandler.KEY_ID + "=?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Note entry = new Note(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        cursor.close();
        return entry;
    }


    // Getting All Entries
    public List<Note> getAllEntries() {


        List<Note> entryList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE;

        Cursor cursor = mDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note entry = new Note();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setTitle(cursor.getString(1));
                entry.setContent(cursor.getString(2));
                entry.setDate(cursor.getString(3));
                entryList.add(entry);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return entry list
        return entryList;
    }


    // Getting entry Count
    public int getEntryCount() {
        int count;

        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE;

        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating single contact
    public int updateEntry(Note entry) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_TITLE, entry.getTitle());
        values.put(DatabaseHandler.KEY_CONTENT, entry.getContent());
        values.put(DatabaseHandler.KEY_DATE, entry.getDate());

        // updating row
        return mDatabase.update(DatabaseHandler.TABLE, values, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(entry.getId()) });
    }

    // Deleting single contact
    public void deleteEntry(Note entry) {

        mDatabase.delete(
                DatabaseHandler.TABLE, DatabaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(entry.getId()) }
        );
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }
}
