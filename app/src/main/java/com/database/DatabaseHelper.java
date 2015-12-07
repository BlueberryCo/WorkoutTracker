package com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by boiko on 12/1/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workouttrackerdatabase";

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
}
