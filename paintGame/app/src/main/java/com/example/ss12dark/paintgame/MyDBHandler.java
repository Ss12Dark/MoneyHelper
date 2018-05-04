package com.example.ss12dark.paintgame;

/**
 * Created by Ss12Dark on 3/9/2018.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;


//this class handles the all database tasks and extends the SQLiteOpenHelper (a build class)
//in this class we must to implement the methods "onCreate" and "onUpgrade" from the SQLiteOpenHelper class
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "EventLab";

    public static final String TABLE_EVENTS = "Events";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "eventName";
    public static final String KEY_DESCRIPTION= "eventDescription";
    public static final String KEY_PRICE = "eventPrice";
    public static final String KEY_TYPE = "eventType";
    public static final String KEY_TYPE2 = "eventType2";
    public static final String KEY_LOCATION = "eventLocation";

    //We need to pass database information along to superclass because the super class doesn't have any default constructor

    public MyDBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //in the first time that we run this app we want to create our table in order to store data
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_PRICE + " REAL, "
                + KEY_TYPE + " INTEGER, "
                + KEY_TYPE2 + " INTEGER, "
                + KEY_LOCATION + " TEXT"+")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }


    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS,null,null);
        db.execSQL("delete from "+ TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        onCreate(db);
    }


    public void addEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, event.getName());
        values.put(KEY_DESCRIPTION, event.getDescription());
        values.put(KEY_PRICE, event.getPrice());
        values.put(KEY_TYPE, event.getType());
        values.put(KEY_TYPE2, event.getType2());
        values.put(KEY_LOCATION, event.getLocation());


        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }


    public boolean deleteEvent(String Name) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_EVENTS, KEY_NAME + "=" + Name, null) > 0;

    }


    public List<Event> getAllEventList() {

        List<Event> EventList = new ArrayList<Event>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Event event = new Event();
                event.setName(cursor.getString(1));
                event.setDescription(cursor.getString(2));
                event.setPrice((cursor.getFloat(3)));
                event.setType(Integer.parseInt(cursor.getString(4)));
                event.setType2(Integer.parseInt(cursor.getString(5)));
                event.setLocation(cursor.getString(6));

                // Adding contact to list
                EventList.add(event);

            } while (cursor.moveToNext());
        }

        // return contact list
        return EventList;
    }

}