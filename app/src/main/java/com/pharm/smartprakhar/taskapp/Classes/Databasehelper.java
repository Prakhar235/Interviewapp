package com.pharm.smartprakhar.taskapp.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pharm.smartprakhar.taskapp.Entityclasses.Image;

import org.json.JSONException;

import java.util.ArrayList;

public class Databasehelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 36;

    // Database Name
    private static final String DATABASE_NAME = "Image_db";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Image.CREATE_TABLE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Image.TABLE_NAME);


        // Create tables again
        onCreate(db);
    }
    public void insertImage(Image image) throws JSONException {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();
        // no need to add them

        values.put(Image.COLUMN_IMAGEID, image.getImageid());
        values.put(Image.COLUMN_TIMESTAMP, image.getTimestamp());
        values.put(Image.COLUMN_PATH, image.getPath());






        // insert row
        db.insertWithOnConflict(Image.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);




        // close db connection
        db .close();


    }

    public ArrayList<Image> getAllImage(String time) {
        ArrayList<Image> imagelist = new ArrayList<>();

        // Select All Query


        String selectQuery = "SELECT  * FROM " + Image.TABLE_NAME + " WHERE " +
               Image.COLUMN_TIMESTAMP + " =? ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{time});


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Image image = new Image();
                image.setImageid(cursor.getString(cursor.getColumnIndex(Image.COLUMN_IMAGEID)));
                image.setTimestamp(cursor.getString(cursor.getColumnIndex(Image.COLUMN_TIMESTAMP)));
                image.setPath(cursor.getString(cursor.getColumnIndex(Image.COLUMN_PATH)));






                imagelist.add(image);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return imagelist;
    }

    public int getImageCount() {
        String countQuery = "SELECT  * FROM " + Image.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;

    }



}
