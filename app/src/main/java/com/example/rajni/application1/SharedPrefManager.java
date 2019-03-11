package com.example.rajni.application1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {


        //the constants
        private static final String SHARED_PREF_NAME = "prefsfile";
        private static final String KEY_FNAME = "keyfname";
        private static final String KEY_MNAME = "keymname";
    private static final String KEY_LNAME = "keylname";
    private static final String KEY_BDATE = "keybdate";
    private static final String KEY_EID = "keyeid";
        private static final String KEY_EMAIL = "keyemail";
        private static final String KEY_GENDER = "keygender";
        private static final String KEY_CONTACT = "keycontact";
    private static final String KEY_PWD = "keypwd";

        private static SharedPrefManager mInstance;
        private static Context mCtx;

        private SharedPrefManager(Context context) {
            mCtx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new SharedPrefManager(context);
            }
            return mInstance;
        }

        //method to let the user login
        //this method will store the user data in shared preferences
        public void userLogin(User user) {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_FNAME, user.getFirstname());
            editor.putString(KEY_MNAME, user.getMiddlename());
            editor.putString(KEY_LNAME, user.getLastname());
            editor.putString(KEY_BDATE, user.getBirthdate());
            editor.putString(KEY_GENDER, user.getGender());
            editor.putString(KEY_EID, user.getEmployeeID());
            editor.putString(KEY_EMAIL, user.getEmailID());
            editor.putString(KEY_CONTACT, user.getContactNo());
            editor.putString(KEY_PWD, user.getPassword());
            editor.apply();
        }

        //this method will checker whether user is already logged in or not
        public boolean isLoggedIn() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_EID, null) != null;
        }

        //this method will give the logged in user
        public User getUser() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return new User(
                    sharedPreferences.getString(KEY_FNAME, null),
                    sharedPreferences.getString(KEY_MNAME, null),
                    sharedPreferences.getString(KEY_LNAME, null),
                    sharedPreferences.getString(KEY_BDATE, null),
                    sharedPreferences.getString(KEY_GENDER, null),
                    sharedPreferences.getString(KEY_EID, null),
                    sharedPreferences.getString(KEY_EMAIL, null),
                    sharedPreferences.getString(KEY_CONTACT, null),
                    sharedPreferences.getString(KEY_PWD, null)

            );
        }

        //this method will logout the user
        public void logout() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            mCtx.startActivity(new Intent(mCtx, login.class));
        }
    }


