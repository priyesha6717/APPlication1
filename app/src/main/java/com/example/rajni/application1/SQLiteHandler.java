package com.example.rajni.application1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "edc";

    // Login table name
    private static final String TABLE_USER = "registration";

    // Login Table Columns names
    private static final String KEY_FNAME = "keyfname";
    private static final String KEY_MNAME = "keymname";
    private static final String KEY_LNAME = "keylname";
    private static final String KEY_BDATE = "keybdate";
    private static final String KEY_EID = "keyeid";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_CONTACT = "keycontact";
    private static final String KEY_PWD = "keypwd";
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_FNAME + " TEXT," + KEY_MNAME + " TEXT,"
                + KEY_LNAME + " TEXT," + KEY_BDATE + " DATE,"
                + KEY_GENDER + " TEXT," + KEY_EID + " VARCHAR PRIMARY KEY," + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_CONTACT + " INTEGER," + KEY_PWD + " VARCHAR " + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String Firstname, String Middlename, String Lastname, String Birthdate, String Gender, String EmployeeID, String EmailID, String ContactNo, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, Firstname); // Name
        values.put(KEY_MNAME, Middlename); // Email
        values.put(KEY_LNAME, Lastname); // Email
        values.put(KEY_BDATE, Birthdate); // Created At
        values.put(KEY_GENDER, Gender); // Name
        values.put(KEY_EID, EmployeeID); // Email
        values.put(KEY_EMAIL, EmailID); // Email
        values.put(KEY_CONTACT, ContactNo);
        values.put(KEY_PWD, Password);// Created At


        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("Firstname", cursor.getString(1));
            user.put("Middlename", cursor.getString(2));
            user.put("Lastname", cursor.getString(3));
            user.put("Birthdate", cursor.getString(4));
            user.put("Gender", cursor.getString(5));
            user.put("EmployeeID", cursor.getString(6));
            user.put("EmailID", cursor.getString(7));
            user.put("ContactNo", cursor.getString(8));
            user.put("Password", cursor.getString(9));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
