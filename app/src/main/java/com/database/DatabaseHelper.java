package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.entities.Client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by boiko on 12/1/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workouttrackerdatabase";

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    //Table names
    private static final String TABLE_CLIENTS = "clients";
    private static final String TABLE_CLIENT_STATS = "client_stats";
    private static final String TABLE_CLIENT_WORKOUT = "client_workout";
    private static final String TABLE_DRAFT_TRAINING_PROGRAMS = "draft_training_programs";
    private static final String TABLE_WORKOUT_OF_THE_DAY = "wod";

    //Clients table column names
    private static final String KEY_ID_CLIENT = "Id";
    private static final String KEY_FIRST_NAME = "FirstName";
    private static final String KEY_LAST_NAME = "LastName";
    private static final String KEY_BIRTH_DATE = "BirthDate";
    private static final String KEY_HEIGHT_CLIENT = "Height";
    private static final String KEY_WEIGHT_CLIENT = "Weight";
    private static final String KEY_PHONE = "Phone";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_TYPE = "Type";

    //ClientStats table column names
    private static final String KEY_ID_CLIENT_STATS = "Id";
    private static final String KEY_STATS_CLIENT_ID = "ClientId";
    private static final String KEY_WEIGHT_STATS = "Weight";
    private static final String KEY_MAX_BACK_SQUAT = "MaxBackSquat";
    private static final String KEY_MAX_FRONT_SQUAT = "MaxFrontSquat";
    private static final String KEY_MAX_DEADLIFT = "MaxDeadlift";
    private static final String KEY_MAX_BENCHPRESS = "MaxBenchpress";
    private static final String KEY_MAX_SHOULDERSPRESS = "MaxShoulderspress";
    private static final String KEY_DATE_STATS = "Date";

    //ClientWorkout table column names
    private static final String KEY_ID_CLIENT_WORKOUT = "Id";
    private static final String KEY_CLIENT_WORKOUT_CLIENT_ID = "ClientId";
    private static final String KEY_DATE_CLIENT_WORKOUT = "Date";
    private static final String KEY_IS_FINISHED = "IsFinished";
    private static final String KEY_CONTENT_CLIENT_WORKOUT = "Content";

    //WOD table column names
    private static final String KEY_ID_WOD = "Id";
    private static final String KEY_DATE_WOD = "Date";
    private static final String KEY_CONTENT_WOD = "Content";

    //DraftTrainingPrograms table column names
    private static final String KEY_ID_DRAFT_TRAINING_PROGRAMS = "Id";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_CONTENT_DRAFT_TRAINING_PROGRAMS = "Content";

    //Table creates

    private static final String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS +
            "(" + KEY_ID_CLIENT + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME + " TEXT," +
            KEY_LAST_NAME + " TEXT," + KEY_BIRTH_DATE + " DATETIME," + KEY_HEIGHT_CLIENT +
            " REAL," + KEY_WEIGHT_CLIENT + " REAL," + KEY_PHONE + " TEXT," + KEY_EMAIL + " TEXT," +
            KEY_TYPE + " INTEGER);";

    private static final String CREATE_TABLE_CLIENT_STATS = "CREATE TABLE " + TABLE_CLIENT_STATS +
            "(" + KEY_ID_CLIENT_STATS + " INTEGER PRIMARY KEY," + KEY_STATS_CLIENT_ID + " INTEGER," +
            KEY_WEIGHT_STATS + " REAL," + KEY_MAX_BACK_SQUAT + " INTEGER," + KEY_MAX_FRONT_SQUAT +
            " INTEGER," + KEY_MAX_DEADLIFT + " INTEGER," + KEY_MAX_BENCHPRESS + " INTEGER," +
            KEY_MAX_SHOULDERSPRESS + " INTEGER," + KEY_DATE_STATS + " DATETIME," +
            "FOREIGN KEY(" + KEY_STATS_CLIENT_ID + ") REFERENCES " + TABLE_CLIENTS + "(" + KEY_ID_CLIENT +
            ");";

    private static final String CREATE_TABLE_CLIENT_WORKOUT = "CREATE TABLE " + TABLE_CLIENT_WORKOUT +
            "(" + KEY_ID_CLIENT_WORKOUT + " INTEGER PRIMARY KEY," + KEY_CLIENT_WORKOUT_CLIENT_ID +
            " INTEGER," + KEY_DATE_CLIENT_WORKOUT + " DATETIME," + KEY_IS_FINISHED + " INTEGER," +
            KEY_CONTENT_CLIENT_WORKOUT + " TEXT," + "FOREIGN KEY(" + KEY_CLIENT_WORKOUT_CLIENT_ID +
            ") REFERENCES " + TABLE_CLIENTS + "(" + KEY_ID_CLIENT + ");";

    private static final String CREATE_TABLE_WOD = "CREATE TABLE " + TABLE_WORKOUT_OF_THE_DAY +
            "(" + KEY_ID_WOD + " INTEGER PRIMARY KEY," + KEY_DATE_WOD + " DATETIME," + KEY_CONTENT_WOD +
            " TEXT);";

    private static final String CREATE_TABLE_DRAFT_TRAINING_PROGRAMS = "CREATE TABLE " + TABLE_DRAFT_TRAINING_PROGRAMS +
            "(" + KEY_ID_DRAFT_TRAINING_PROGRAMS + " INTEGER PRIMARY KEY," + KEY_CATEGORY + " TEXT," +
            KEY_TITLE + " TEXT," + KEY_CONTENT_DRAFT_TRAINING_PROGRAMS + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_CLIENT_STATS);
        db.execSQL(CREATE_TABLE_CLIENT_WORKOUT);
        db.execSQL(CREATE_TABLE_WOD);
        db.execSQL(CREATE_TABLE_DRAFT_TRAINING_PROGRAMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private Date parseStringToDate(String input){
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS", Locale.ENGLISH);
        try {
            return  format.parse(input);
        } catch (ParseException e) {
            Log.e(LOG, e.getStackTrace().toString());
        }

        return null;
    }

    private String dateToString(Date input){
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS", Locale.ENGLISH);
        return format.format(input);
    }

    //region Client methods

    public Client getOwner(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + KEY_TYPE +
                " = " + Client.CLIENT_TYPE_OWNER;

        Cursor c = db.rawQuery(query, null);

        Client result = null;

        if(c.moveToFirst()){
            result = new Client();
            result.setId(c.getInt(c.getColumnIndex(KEY_ID_CLIENT)));
            result.setFirstName(c.getString(c.getColumnIndex(KEY_FIRST_NAME)));
            result.setLastName(c.getString(c.getColumnIndex(KEY_LAST_NAME)));
            result.setBirthDate(parseStringToDate(c.getString(c.getColumnIndex(KEY_BIRTH_DATE))));
            result.setHeight(c.getFloat(c.getColumnIndex(KEY_HEIGHT_CLIENT)));
            result.setWeight(c.getFloat(c.getColumnIndex(KEY_WEIGHT_CLIENT)));
            result.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
            result.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
            result.setType(Client.CLIENT_TYPE_OWNER);
        }

        return result;
    }

    public List<Client> getAllClients(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + KEY_TYPE +
                " = " + Client.CLIENT_TYPE_CLIENT;

        List<Client> results = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Client result = new Client();
                result.setId(c.getInt(c.getColumnIndex(KEY_ID_CLIENT)));
                result.setFirstName(c.getString(c.getColumnIndex(KEY_FIRST_NAME)));
                result.setLastName(c.getString(c.getColumnIndex(KEY_LAST_NAME)));
                result.setBirthDate(parseStringToDate(c.getString(c.getColumnIndex(KEY_BIRTH_DATE))));
                result.setHeight(c.getFloat(c.getColumnIndex(KEY_HEIGHT_CLIENT)));
                result.setWeight(c.getFloat(c.getColumnIndex(KEY_WEIGHT_CLIENT)));
                result.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                result.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                result.setType(Client.CLIENT_TYPE_CLIENT);

                results.add(result);
            }while (c.moveToNext());
        }

        return results;
    }

    public Client createClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, client.getFirstName());
        values.put(KEY_LAST_NAME, client.getLastName());
        values.put(KEY_BIRTH_DATE, dateToString(client.getBirthDate()));
        values.put(KEY_HEIGHT_CLIENT, client.getHeight());
        values.put(KEY_WEIGHT_CLIENT, client.getWeight());
        values.put(KEY_PHONE, client.getPhone());
        values.put(KEY_EMAIL, client.getEmail());
        values.put(KEY_TYPE, Client.CLIENT_TYPE_CLIENT);

        int clientId = (int)db.insert(TABLE_CLIENTS, null, values);

        return client;
    }

    //endregion
}
