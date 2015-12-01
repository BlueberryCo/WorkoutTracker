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

    private static final String TABLE_CLIENTS = "clients";
    private static final String TABLE_CLIENT_STATS = "client_stats";
    private static final String TABLE_CLIENT_WORKOUT = "client_workout";
    private static final String TABLE_DRAFT_TRAINING_PROGRAMS = "draft_training_programs";
    private static final String TABLE_WORKOUT_OF_THE_DAY = "wod";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
