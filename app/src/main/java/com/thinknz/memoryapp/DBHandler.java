package com.thinknz.memoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liamtubs on 18/07/2016.
 */
public class DBHandler extends SQLiteOpenHelper{
    //Database name and version
    final static int DATABASE_VERSION = 1;
    final static String DATABASE_NAME = "Calender";

    //Column names for Meeting table
    final static String TABLE_MEETING = "Meeting";
    final static String COLUMN_MEETING_ID = "MeetingId";
    final static String COLUMN_MEETING_DATE = "Date";
    final static String COLUMN_MEETING_NAME = "Name";
    final static String COLUMN_MEETING_TIME = "Time";
    final static String COLUMN_MEETING_LOCATION = "Location";
    final static String COLUMN_MEETING_NOTES = "Notes";

    //Column names for Medication table
    final static String TABLE_MEDICATION = "Medication";
    final static String COLUMN_MEDICATION_ID = "MedicationId";
    final static String COLUMN_MEDICATION_NAME = "Name";
    final static String COLUMN_MEDICATION_QUANTITY = "Quantity";
    final static String COLUMN_MEDICATION_END_DATE = "EndDate";
    final static String COLUMN_MEDICATION_REMINDER_TIME = "ReminderTime";
    final static String COLUMN_MEDICATION_REMINDER_MESSAGE = "ReminderMessage";

    //Column names for MedicationTime table
    final static String TABLE_MEDICATION_TIME = "MedicationTime";
    final static String COLUMN_MEDICATION_TIME_ID = "MedicationTimeId";
    final static String COLUMN_MEDICATION_TIME_MEDICATION_ID = "MedicationId";
    final static String COLUMN_MEDICATION_TIME_TIME = "Time";

    //Column names for List table
    final static String TABLE_LIST = "List";
    final static String COLUMN_LIST_ID = "ListId";
    final static String COLUMN_LIST_END_DATE = "EndDate";

    //Column names for ListItem table
    final static String TABLE_LIST_ITEM = "ListItem";
    final static String COLUMN_LIST_ITEM_ID = "ListItemId";
    final static String COLUMN_LIST_ITEM_LIST_ID = "ListId";
    final static String COLUMN_LIST_ITEM_NAME = "Name";
    final static String COLUMN_LIST_ITEM_DESCRIPTION = "Description";

    //Column names for ShoppingListItem table
    final static String TABLE_SHOPPING_LIST_ITEM = "ShoppingListItem";
    final static String COLUMN_SL_SHOPPING_ITEM_ID = "ShoppingItemId";
    final static String COLUMN_SL_LIST_ID = "ListId";
    final static String COLUMN_SL_NOTES = "Notes";

    //Column names for ShoppingItem table
    final static String TABLE_SHOPPING_ITEM = "ShoppingListItem";
    final static String COLUMN_SHOPPING_ITEM_ID = "ShoppingItemId";
    final static String COLUMN_SHOPPING_ITEM_CATEGORY_ID = "CategoryId";
    final static String COLUMN_SHOPPING_ITEM_NAME = "Name";
    final static String COLUMN_SHOPPING_ITEM_IMAGE = "Image";

    //Column names for Category table
    final static String TABLE_CATEGORY = "Category";
    final static String COLUMN_CATEGORY_ID = "CategoryId";
    final static String COLUMN_CATEGORY_NAME = "Name";
    final static String COLUMN_CATEGORY_IMAGE = "Image";

    //The database name and version variables are used instead of the name and version that gets passed as parameters
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates Meeting table
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_MEETING + "(" +
                COLUMN_MEETING_ID + " TEXT PRIMARY KEY, " +
                COLUMN_MEETING_DATE + " TEXT, " +
                COLUMN_MEETING_NAME + " TEXT, " +
                COLUMN_MEETING_TIME + " TEXT, " +
                COLUMN_MEETING_LOCATION + " TEXT, " +
                COLUMN_MEETING_NOTES + " TEXT" +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates Medication table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_MEDICATION + "(" +
                COLUMN_MEDICATION_ID + " TEXT PRIMARY KEY, " +
                COLUMN_MEDICATION_QUANTITY + " TEXT, " +
                COLUMN_MEDICATION_END_DATE + " TEXT, " +
                COLUMN_MEDICATION_REMINDER_TIME + " TEXT, " +
                COLUMN_MEDICATION_REMINDER_MESSAGE + " TEXT, " +
                COLUMN_MEDICATION_NAME + " TEXT" +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates MedicationTime table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_MEDICATION_TIME + "(" +
                COLUMN_MEDICATION_TIME_ID + " TEXT PRIMARY KEY, " +
                COLUMN_MEDICATION_TIME_MEDICATION_ID + " TEXT, " +
                COLUMN_MEDICATION_TIME_TIME + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_MEDICATION_TIME_MEDICATION_ID + ") REFERENCES "+ TABLE_MEDICATION +"("+COLUMN_MEDICATION_ID+")" +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates List table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_LIST + "(" +
                COLUMN_LIST_ID + " TEXT PRIMARY KEY, " +
                COLUMN_LIST_END_DATE + " TEXT " +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates ListItem table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_LIST_ITEM + "(" +
                COLUMN_LIST_ITEM_ID + " TEXT PRIMARY KEY, " +
                COLUMN_LIST_ITEM_LIST_ID + " TEXT, " +
                COLUMN_LIST_ITEM_NAME + " TEXT, " +
                COLUMN_LIST_ITEM_DESCRIPTION + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_LIST_ITEM_LIST_ID + ") REFERENCES "+ TABLE_LIST +"("+COLUMN_LIST_ID+")" +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates Category table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY + "(" +
                COLUMN_CATEGORY_ID + " TEXT PRIMARY KEY, " +
                COLUMN_CATEGORY_NAME + " TEXT, " +
                COLUMN_CATEGORY_IMAGE + " TEXT " +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates ShoppingItem table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_SHOPPING_ITEM + "(" +
                COLUMN_SHOPPING_ITEM_ID + " TEXT PRIMARY KEY, " +
                COLUMN_SHOPPING_ITEM_CATEGORY_ID + " TEXT, " +
                COLUMN_SHOPPING_ITEM_NAME + " TEXT, " +
                COLUMN_SHOPPING_ITEM_IMAGE + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_SHOPPING_ITEM_CATEGORY_ID + ") REFERENCES "+ TABLE_CATEGORY +"("+COLUMN_CATEGORY_ID+")" +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);

        //Creates ShoppingListTable
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_SHOPPING_LIST_ITEM + "(" +
                COLUMN_SL_SHOPPING_ITEM_ID + " TEXT, " +
                COLUMN_SL_LIST_ID + " TEXT, " +
                COLUMN_SL_NOTES + " TEXT, " +
                "PRIMARY KEY (" + COLUMN_SL_SHOPPING_ITEM_ID + ", " + COLUMN_SL_LIST_ID + ")," +
                "FOREIGN KEY (" + COLUMN_SL_SHOPPING_ITEM_ID + ") REFERENCES "+ TABLE_SHOPPING_ITEM +"("+COLUMN_SHOPPING_ITEM_ID+")," +
                "FOREIGN KEY (" + COLUMN_SL_LIST_ID + ") REFERENCES "+ TABLE_LIST +"("+COLUMN_LIST_ID+")" +
                ");";

        Log.d("LOG", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEETING );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATION_TIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST_ITEM);

        onCreate(db);
    }

    //GET ID METHODS------------------------------------------------------------------------------//
    public String getMeetingId(){
        SQLiteDatabase db = getWritableDatabase();

        //Query to select all meetings
        String query = "SELECT * FROM " + TABLE_MEETING;
        //Saves results of the query in the cursor variable
        Cursor c = db.rawQuery(query, null);

        //Selects the last record in the database and returns its ID + 1
        if (!c.isAfterLast()) {
            c.moveToLast();

            String id = String.valueOf(Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_MEETING_ID))) + 1);

            c.close();
            db.close();

            return id;
        }

        c.close();
        db.close();

        //If no records found then 1 is the value returned
        return "1";
    }

    //INSERT METHODS------------------------------------------------------------------------------//
    public void insertMeeting(Meeting meeting){
        //Tries to find the meeting in the database
        Meeting existingMeeting = selectMeeting(meeting.id);

        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();

        //Sets values that will be inserted or updated
        values.put(COLUMN_MEETING_ID, meeting.id);
        values.put(COLUMN_MEETING_DATE, meeting.date);
        values.put(COLUMN_MEETING_NAME, meeting.name);
        values.put(COLUMN_MEETING_TIME, meeting.time);
        values.put(COLUMN_MEETING_LOCATION, meeting.location);
        values.put(COLUMN_MEETING_NOTES, meeting.notes);

        //If meeting was not in the database then a new meeting is inserted
        //If the meeting is already in the database then it is updated with the new values
        if (existingMeeting == null){
            db.insert(TABLE_MEETING, null, values);
            db.close();
        }else{
            db.update(TABLE_MEETING, values, COLUMN_MEETING_ID + " = \"" + meeting.id + "\"", null);
            db.close();
        }
    }

    public void insertMedication(Medication medication){
        //Tries to find the medication in the database
        Medication existingMeeting = selectMedication(medication.id);

        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();

        //Sets values that will be inserted or updated
        values.put(COLUMN_MEDICATION_ID, medication.id);
        values.put(COLUMN_MEDICATION_QUANTITY, medication.quantity);
        values.put(COLUMN_MEDICATION_END_DATE, medication.endDate);
        values.put(COLUMN_MEDICATION_REMINDER_TIME, medication.reminderTime);
        values.put(COLUMN_MEDICATION_REMINDER_MESSAGE, medication.reminderMessage);
        values.put(COLUMN_MEDICATION_NAME, medication.name);

        //If medication was not in the database then a new medication is inserted
        //If the medication is already in the database then it is updated with the new values
        if (existingMeeting == null){
            db.insert(TABLE_MEDICATION, null, values);
            db.close();
        }else{
            db.update(TABLE_MEDICATION, values, COLUMN_MEDICATION_ID + " = \"" + medication.id + "\"", null);
            db.close();
        }
    }

    //SELECT BY ID METHODS------------------------------------------------------------------------//
    public Meeting selectMeeting(String meetingId){
        SQLiteDatabase db = getWritableDatabase();

        //Query to select a meeting with the same meetingId given as a parameter
        String query = "SELECT * FROM " + TABLE_MEETING + " WHERE " + COLUMN_MEETING_ID + " = \"" + meetingId + "\"";
        //Saves results of the query in the cursor variable
        Cursor c = db.rawQuery(query, null);

        //If the meeting is found in the database then a new meeting object is returned
        if (!c.isAfterLast()) {
            c.moveToFirst();

            Meeting temp = new Meeting(c.getString(c.getColumnIndex(COLUMN_MEETING_ID)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_DATE)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_TIME)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_LOCATION)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_NOTES)));


            c.close();
            db.close();
            return temp;
        }

        c.close();
        db.close();

        //If the meeting is not found then null is returned
        return null;
    }

    public Medication selectMedication(String medicationId){
        SQLiteDatabase db = getWritableDatabase();

        //Query to select a medication with the same medicationId given as a parameter
        String query = "SELECT * FROM " + TABLE_MEDICATION + " WHERE " + COLUMN_MEDICATION_ID + " = \"" + medicationId + "\"";
        //Saves results of the query in the cursor variable
        Cursor c = db.rawQuery(query, null);

        //If the medication is found in the database then a new medication object is returned
        if (!c.isAfterLast()) {
            c.moveToFirst();

            Medication temp = new Medication(c.getString(c.getColumnIndex(COLUMN_MEDICATION_ID)),
                    c.getString(c.getColumnIndex(COLUMN_MEDICATION_QUANTITY)),
                    c.getString(c.getColumnIndex(COLUMN_MEDICATION_END_DATE)),
                    c.getString(c.getColumnIndex(COLUMN_MEDICATION_REMINDER_TIME)),
                    c.getString(c.getColumnIndex(COLUMN_MEDICATION_REMINDER_MESSAGE)),
                    c.getString(c.getColumnIndex(COLUMN_MEDICATION_NAME)));

            temp.times = selectMedicationTimes(temp.id);

            c.close();
            db.close();
            return temp;
        }

        c.close();
        db.close();

        //If the medication is not found then null is returned
        return null;
    }

    //SELECT LIST BY ID METHODS-------------------------------------------------------------------//
    public List<MedicationTime> selectMedicationTimes(String medicationId){
        SQLiteDatabase db = getWritableDatabase();
        List<MedicationTime> temp = new ArrayList<MedicationTime>();

        //Query to select a medication with the same medicationId given as a parameter
        String query = "SELECT * FROM " + TABLE_MEDICATION + " WHERE " + COLUMN_MEDICATION_ID + " = \"" + medicationId + "\"";
        //Saves results of the query in the cursor variable
        Cursor c = db.rawQuery(query, null);

        //If the medication is found in the database then a new medication object is returned
        while (!c.isAfterLast()) {
            c.moveToFirst();

            temp.add(new MedicationTime(c.getString(c.getColumnIndex(COLUMN_MEDICATION_TIME_ID)),
                    c.getString(c.getColumnIndex(COLUMN_MEDICATION_TIME_TIME))));

            c.moveToNext();
        }

        c.close();
        db.close();

        //If the medication is not found then null is returned
        return temp;
    }

    //DELETE BY ID METHODS------------------------------------------------------------------------//
    public void deleteMeeting(String meetingId){
        SQLiteDatabase db = getWritableDatabase();

        //Query to delete a meeting with the same meetingId given as a parameter
        String query = "DELETE FROM " + TABLE_MEETING + " WHERE " + COLUMN_MEETING_ID + " = \"" + meetingId + "\"";
        db.execSQL(query);
    }

    public void deleteMedication(String medicationId){
        SQLiteDatabase db = getWritableDatabase();

        //Query to delete a meeting with the same meetingId given as a parameter
        String query = "DELETE FROM " + TABLE_MEDICATION + " WHERE " + COLUMN_MEDICATION_END_DATE + " = \"" + medicationId + "\"";
        db.execSQL(query);
    }

    //SELECT BY DATE METHODS----------------------------------------------------------------------//
    public List<Meeting> selectMeetingsByDate(String date){
        SQLiteDatabase db = getWritableDatabase();
        List<Meeting> temp = new ArrayList<>();

        //Query to select all meetings on a particular date
        String query = "SELECT * FROM " + TABLE_MEETING + " WHERE " + COLUMN_MEETING_DATE + " = \"" + date + "\"";
        //Saves results of the query in the cursor variable
        Cursor c = db.rawQuery(query, null);

        //If any meetings are found then cursor moves to the first record
        if(c.getCount() > 0){
            c.moveToFirst();
        }

        //For each meeting that is found a new meeting object is added to the temp list
        while (!c.isAfterLast()) {
            temp.add(new Meeting(c.getString(c.getColumnIndex(COLUMN_MEETING_ID)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_DATE)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_TIME)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_LOCATION)),
                    c.getString(c.getColumnIndex(COLUMN_MEETING_NOTES))));

            c.moveToNext();
        }

        c.close();
        db.close();

        //If no meetings are found then null is returned
        return temp;
    }
}
