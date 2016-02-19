package blueberryco.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blueberryco.entities.Client;
import blueberryco.entities.ClientStats;
import blueberryco.entities.ClientWorkout;
import blueberryco.entities.Exercise;
import blueberryco.entities.Set;
import blueberryco.entities.Util;

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
    private static final String TABLE_EXERCISES = "exercises";
    private static final String TABLE_SETS = "sets";

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

    //WOD table column names
    private static final String KEY_ID_WOD = "Id";
    private static final String KEY_DATE_WOD = "Date";
    private static final String KEY_CONTENT_WOD = "Content";

    //DraftTrainingPrograms table column names
    private static final String KEY_ID_DRAFT_TRAINING_PROGRAMS = "Id";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_CONTENT_DRAFT_TRAINING_PROGRAMS = "Content";

    //Exercises table column names
    private static final String KEY_ID_EXERCISES = "Id";
    private static final String KEY_EXERCISE_EXERCISES = "Exercise";
    private static final String KEY_CLIENT_WORKOUT_ID = "ClientWorkoutId";
    private static final String KEY_DESCRIPTION = "Description";

    //Sets table column names
    private static final String KEY_ID_SETS = "Id";
    private static final String KEY_EXERCISE_ID = "ExerciseId";
    private static final String KEY_EXERCISE_SETS = "Exercise";
    private static final String KEY_SETS = "Sets";
    private static final String KEY_REPS = "Reps";
    private static final String KEY_WEIGHT_SETS = "Weight";


    //Table creates

    private static final String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS +
            "(" + KEY_ID_CLIENT + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRST_NAME + " TEXT," +
            KEY_LAST_NAME + " TEXT," + KEY_BIRTH_DATE + " DATETIME," + KEY_HEIGHT_CLIENT +
            " REAL," + KEY_WEIGHT_CLIENT + " REAL," + KEY_PHONE + " TEXT," + KEY_EMAIL + " TEXT," +
            KEY_TYPE + " INTEGER);";

    private static final String CREATE_TABLE_CLIENT_STATS = "CREATE TABLE " + TABLE_CLIENT_STATS +
            "(" + KEY_ID_CLIENT_STATS + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STATS_CLIENT_ID + " INTEGER," +
            KEY_WEIGHT_STATS + " REAL," + KEY_MAX_BACK_SQUAT + " INTEGER," + KEY_MAX_FRONT_SQUAT +
            " INTEGER," + KEY_MAX_DEADLIFT + " INTEGER," + KEY_MAX_BENCHPRESS + " INTEGER," +
            KEY_MAX_SHOULDERSPRESS + " INTEGER," + KEY_DATE_STATS + " DATETIME," +
            "FOREIGN KEY(" + KEY_STATS_CLIENT_ID + ") REFERENCES " + TABLE_CLIENTS + "(" + KEY_ID_CLIENT +
            "));";

    private static final String CREATE_TABLE_CLIENT_WORKOUT = "CREATE TABLE " + TABLE_CLIENT_WORKOUT +
            "(" + KEY_ID_CLIENT_WORKOUT + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CLIENT_WORKOUT_CLIENT_ID +
            " INTEGER," + KEY_DATE_CLIENT_WORKOUT + " DATETIME," + KEY_IS_FINISHED + " INTEGER,"
            + "FOREIGN KEY(" + KEY_CLIENT_WORKOUT_CLIENT_ID +
            ") REFERENCES " + TABLE_CLIENTS + "(" + KEY_ID_CLIENT + "));";

    private static final String CREATE_TABLE_WOD = "CREATE TABLE " + TABLE_WORKOUT_OF_THE_DAY +
            "(" + KEY_ID_WOD + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE_WOD + " DATETIME," + KEY_CONTENT_WOD +
            " TEXT);";

    private static final String CREATE_TABLE_DRAFT_TRAINING_PROGRAMS = "CREATE TABLE " + TABLE_DRAFT_TRAINING_PROGRAMS +
            "(" + KEY_ID_DRAFT_TRAINING_PROGRAMS + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CATEGORY + " TEXT," +
            KEY_TITLE + " TEXT," + KEY_CONTENT_DRAFT_TRAINING_PROGRAMS + " TEXT);";

    private static final String CREATE_TABLE_EXERCISES = "CREATE TABLE " + TABLE_EXERCISES + "(" + KEY_ID_EXERCISES + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_EXERCISE_EXERCISES + " TEXT," + KEY_CLIENT_WORKOUT_ID + " INTEGER," + KEY_DESCRIPTION + " TEXT," +
            "FOREIGN KEY(" + KEY_CLIENT_WORKOUT_ID + ") REFERENCES " + TABLE_CLIENT_WORKOUT + "(" + KEY_ID_CLIENT_WORKOUT + "));";

    private static final String CREATE_TABLE_SETS = "CREATE TABLE " + TABLE_SETS + "(" + KEY_ID_SETS +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_EXERCISE_ID + " INTEGER," + KEY_EXERCISE_SETS + " TEXT," + KEY_SETS + " INTEGER, " +
            KEY_REPS + " INTEGER," + KEY_WEIGHT_SETS + " INTEGER," + "FOREIGN KEY(" + KEY_EXERCISE_ID + ") REFERENCES " + TABLE_EXERCISES + "(" +
            KEY_ID_EXERCISES + "));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

     public void initialLoad() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select count(*) as nb  from client_workout;";
        Cursor res = db.rawQuery(query, null);
        int count = 0;

        if (res.moveToFirst()) {
            Log.d("1-> "," prediiii");
            count = res.getInt(res.getColumnIndex("nb"));
            Log.d("res.getInt(1)-> ", res.getColumnIndex("nb")+"");
            if (count == 0) {
                String insertClient1 = "insert into clients values (3,'Ivan','Ivanov',strftime('%Y-%m-%d %H:%M:%f', '1991-03-01 13:01:01.123'),167,60,'021155221','ivan@ivanov.bg',2);";
                String insertClient2 = "insert into clients values (4,'Maq','Ivanova',strftime('%Y-%m-%d %H:%M:%f', '1989-03-09 13:01:01.123'),165,55,'0877445588','maq@ivanova.bg',2);";
                String insClientStat1 = "insert into client_stats values (5,3,60,50,20,25,20,35,strftime('%Y-%m-%d %H:%M:%f', '2016-01-09 13:01:01.123'));";
                String insClientStat2 = "insert into client_stats values (6,3,55,25,20,25,20,35,strftime('%Y-%m-%d %H:%M:%f', '2016-01-20 13:01:01.123'));";
                String insWork1 = "insert into client_workout values (5,3,strftime('%Y-%m-%d %H:%M:%f', '2016-02-10 13:01:01.123'),1);";
                String insWork11 = "insert into client_workout values (6,3,strftime('%Y-%m-%d %H:%M:%f', '2016-02-18 13:01:01.123'),1);";
                String insWork2 = "insert into client_workout values (7,4,strftime('%Y-%m-%d %H:%M:%f', '2016-01-10 13:01:01.123'),1);";
                String insWork22 = "insert into client_workout values (8,4,strftime('%Y-%m-%d %H:%M:%f', '2016-01-18 13:01:01.123'),1);";
                String insEx1="insert into exercises values (9,1,6,'първа тренировка');";
                String insEx11 = "insert into exercises values (11,6,6,'първа тренировка')";
                String insEx2="insert into exercises values (10,2,7,'първа тренировка');";
                String insSets1="insert into sets values (3,9,1,5,10,15);";
                String insSets11="insert into sets values (4,11,6,7,10,15);";
                db.execSQL(insertClient1);
                db.execSQL(insertClient2);
                db.execSQL(insClientStat1);
                db.execSQL(insClientStat2);
                db.execSQL(insWork1);
                db.execSQL(insWork11);
                db.execSQL(insWork2);
                db.execSQL(insWork22);
                db.execSQL(insEx1);
                db.execSQL(insEx11);
                db.execSQL(insEx2);
                db.execSQL(insSets1);
                db.execSQL(insSets11);

                Log.d("2-> ", " sled");
            }
        }


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("1-> ", CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_CLIENTS);

        Log.d("2-> ", CREATE_TABLE_CLIENT_STATS);
        db.execSQL(CREATE_TABLE_CLIENT_STATS);

        Log.i("3-> ", CREATE_TABLE_CLIENT_WORKOUT);
        db.execSQL(CREATE_TABLE_CLIENT_WORKOUT);

        Log.i("4-> ", CREATE_TABLE_WOD);
        db.execSQL(CREATE_TABLE_WOD);

        Log.i("5-> ", CREATE_TABLE_DRAFT_TRAINING_PROGRAMS);
        db.execSQL(CREATE_TABLE_DRAFT_TRAINING_PROGRAMS);

        Log.i("6-> ", CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_EXERCISES);

        Log.i("7-> ", CREATE_TABLE_SETS);
        db.execSQL(CREATE_TABLE_SETS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private Date parseStringToDate(String input) {
        return Util.parseStringToDate(input);
    }

    private String dateToString(Date input) {
        return Util.dateToString(input);
    }

    private boolean isStringNullOrEmpty(String input) {
        return Util.isNullOrEmptyString(input);
    }

    //region Client methods

    public Client getOwner() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + KEY_TYPE +
                " = " + Client.CLIENT_TYPE_OWNER;

        Cursor c = db.rawQuery(query, null);

        Client result = null;

        if (c.moveToFirst()) {
            result = new Client();
            result.setId(c.getInt(c.getColumnIndex(KEY_ID_CLIENT)));
            result.setFirstName(c.getString(c.getColumnIndex(KEY_FIRST_NAME)));
            result.setLastName(c.getString(c.getColumnIndex(KEY_LAST_NAME)));
            if (!c.isNull(c.getColumnIndex(KEY_BIRTH_DATE))) {
                result.setBirthDate(parseStringToDate(c.getString(c.getColumnIndex(KEY_BIRTH_DATE))));
            }
            if (!c.isNull(c.getColumnIndex(KEY_HEIGHT_CLIENT))) {
                result.setHeight(c.getFloat(c.getColumnIndex(KEY_HEIGHT_CLIENT)));
            }
            if (!c.isNull(c.getColumnIndex(KEY_WEIGHT_CLIENT))) {
                result.setWeight(c.getFloat(c.getColumnIndex(KEY_WEIGHT_CLIENT)));
            }
            if (!c.isNull(c.getColumnIndex(KEY_PHONE))) {
                result.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
            }
            if (!c.isNull(c.getColumnIndex(KEY_EMAIL))) {
                result.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
            }
            result.setType(Client.CLIENT_TYPE_OWNER);
        }

        return result;
    }

    public List<Client> getAllClients() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + KEY_TYPE +
                " = " + Client.CLIENT_TYPE_CLIENT;

        List<Client> results = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Client result = new Client();
                result.setId(c.getInt(c.getColumnIndex(KEY_ID_CLIENT)));
                result.setFirstName(c.getString(c.getColumnIndex(KEY_FIRST_NAME)));
                result.setLastName(c.getString(c.getColumnIndex(KEY_LAST_NAME)));
                if (!c.isNull(c.getColumnIndex(KEY_BIRTH_DATE))) {
                    result.setBirthDate(parseStringToDate(c.getString(c.getColumnIndex(KEY_BIRTH_DATE))));
                }
                if (!c.isNull(c.getColumnIndex(KEY_HEIGHT_CLIENT))) {
                    result.setHeight(c.getFloat(c.getColumnIndex(KEY_HEIGHT_CLIENT)));
                }
                if (!c.isNull(c.getColumnIndex(KEY_WEIGHT_CLIENT))) {
                    result.setWeight(c.getFloat(c.getColumnIndex(KEY_WEIGHT_CLIENT)));
                }
                if (!c.isNull(c.getColumnIndex(KEY_PHONE))) {
                    result.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                }
                if (!c.isNull(c.getColumnIndex(KEY_EMAIL))) {
                    result.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                }
                result.setType(Client.CLIENT_TYPE_CLIENT);

                results.add(result);
            } while (c.moveToNext());
        }

        return results;
    }

    public Client createClient(Client client) throws OwnerExistsException {
        SQLiteDatabase db = this.getWritableDatabase();

        if (client.getType() == Client.CLIENT_TYPE_OWNER && getOwner() != null) {
            throw new OwnerExistsException("The owner already exists!");
        }

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, client.getFirstName());
        values.put(KEY_LAST_NAME, client.getLastName());
        if (client.getBirthDate() != null) {
            values.put(KEY_BIRTH_DATE, dateToString(client.getBirthDate()));
        } else {
            values.putNull(KEY_BIRTH_DATE);
        }
        if (client.getHeight() != null) {
            values.put(KEY_HEIGHT_CLIENT, client.getHeight());
        } else {
            values.putNull(KEY_HEIGHT_CLIENT);
        }
        if (client.getWeight() != null) {
            values.put(KEY_WEIGHT_CLIENT, client.getWeight());
        } else {
            values.putNull(KEY_WEIGHT_CLIENT);
        }
        if (!isStringNullOrEmpty(client.getPhone())) {
            values.put(KEY_PHONE, client.getPhone());
        } else {
            values.putNull(KEY_PHONE);
        }
        if (!isStringNullOrEmpty(client.getEmail())) {
            values.put(KEY_EMAIL, client.getEmail());
        } else {
            values.putNull(KEY_EMAIL);
        }
        values.put(KEY_TYPE, client.getType());

        int clientId = (int) db.insert(TABLE_CLIENTS, null, values);

        client.setId(clientId);
        return client;
    }

    public void updateClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, client.getFirstName());
        values.put(KEY_LAST_NAME, client.getLastName());
        if (client.getBirthDate() != null) {
            values.put(KEY_BIRTH_DATE, dateToString(client.getBirthDate()));
        } else {
            values.putNull(KEY_BIRTH_DATE);
        }
        if (client.getHeight() != null) {
            values.put(KEY_HEIGHT_CLIENT, client.getHeight());
        } else {
            values.putNull(KEY_HEIGHT_CLIENT);
        }
        if (client.getWeight() != null) {
            values.put(KEY_WEIGHT_CLIENT, client.getWeight());
        } else {
            values.putNull(KEY_WEIGHT_CLIENT);
        }
        if (!isStringNullOrEmpty(client.getPhone())) {
            values.put(KEY_PHONE, client.getPhone());
        } else {
            values.putNull(KEY_PHONE);
        }
        if (!isStringNullOrEmpty(client.getEmail())) {
            values.put(KEY_EMAIL, client.getEmail());
        } else {
            values.putNull(KEY_EMAIL);
        }
        values.put(KEY_TYPE, client.getType());

        db.update(TABLE_CLIENTS, values, KEY_ID_CLIENT + " = ?",
                new String[]{String.valueOf(client.getId())});
    }

    @Deprecated
    public void deleteClient(int clientId) {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean hasErrors = false;

        db.beginTransaction();

        try {
            db.delete(TABLE_CLIENT_STATS, KEY_STATS_CLIENT_ID + " = ?",
                    new String[]{String.valueOf(clientId)});

            db.delete(TABLE_CLIENT_WORKOUT, KEY_CLIENT_WORKOUT_CLIENT_ID + " = ?",
                    new String[]{String.valueOf(clientId)});

            db.delete(TABLE_CLIENTS, KEY_ID_CLIENT + " = ?",
                    new String[]{String.valueOf(clientId)});
        } catch (Exception e) {
            hasErrors = true;
            Log.e(LOG, e.getStackTrace().toString());
        }

        if (!hasErrors) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    //endregion

    //region Client stats methods

    public List<ClientStats> getClientStatsForClient(int clientId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENT_STATS + " WHERE " +
                KEY_STATS_CLIENT_ID + " = " + String.valueOf(clientId);

        List<ClientStats> results = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                ClientStats result = new ClientStats();
                result.setId(c.getInt(c.getColumnIndex(KEY_ID_CLIENT_STATS)));
                result.setClientId(c.getInt(c.getColumnIndex(KEY_STATS_CLIENT_ID)));
                if (!c.isNull(c.getColumnIndex(KEY_WEIGHT_STATS))) {
                    result.setWeight(c.getFloat(c.getColumnIndex(KEY_WEIGHT_STATS)));
                }

                if (!c.isNull(c.getColumnIndex(KEY_MAX_BACK_SQUAT))) {
                    result.setMaxBackSquat(c.getInt(c.getColumnIndex(KEY_MAX_BACK_SQUAT)));
                }

                if (!c.isNull(c.getColumnIndex(KEY_MAX_FRONT_SQUAT))) {
                    result.setMaxFrontSquat(c.getInt(c.getColumnIndex(KEY_MAX_FRONT_SQUAT)));
                }

                if (!c.isNull(c.getColumnIndex(KEY_MAX_DEADLIFT))) {
                    result.setMaxDeadlift(c.getInt(c.getColumnIndex(KEY_MAX_DEADLIFT)));
                }

                if (!c.isNull(c.getColumnIndex(KEY_MAX_BENCHPRESS))) {
                    result.setMaxBenchpress(c.getInt(c.getColumnIndex(KEY_MAX_BENCHPRESS)));
                }

                if (!c.isNull(c.getColumnIndex(KEY_MAX_SHOULDERSPRESS))) {
                    result.setMaxShoulderspress(c.getInt(c.getColumnIndex(KEY_MAX_SHOULDERSPRESS)));
                }
                result.setDate(parseStringToDate(c.getString(c.getColumnIndex(KEY_DATE_STATS))));

                results.add(result);
            } while (c.moveToNext());
        }

        return results;
    }

    public ClientStats createClientStats(ClientStats clientStats) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATS_CLIENT_ID, clientStats.getClientId());
        if (clientStats.getWeight() != null) {
            values.put(KEY_WEIGHT_STATS, clientStats.getWeight());
        } else {
            values.putNull(KEY_WEIGHT_STATS);
        }
        if (clientStats.getMaxBackSquat() != null) {
            values.put(KEY_MAX_BACK_SQUAT, clientStats.getMaxBackSquat());
        } else {
            values.putNull(KEY_MAX_BACK_SQUAT);
        }
        if (clientStats.getMaxFrontSquat() != null) {
            values.put(KEY_MAX_FRONT_SQUAT, clientStats.getMaxFrontSquat());
        } else {
            values.putNull(KEY_MAX_FRONT_SQUAT);
        }
        if (clientStats.getMaxDeadlift() != null) {
            values.put(KEY_MAX_DEADLIFT, clientStats.getMaxDeadlift());
        } else {
            values.putNull(KEY_MAX_DEADLIFT);
        }
        if (clientStats.getMaxBenchpress() != null) {
            values.put(KEY_MAX_BENCHPRESS, clientStats.getMaxBenchpress());
        } else {
            values.putNull(KEY_MAX_BENCHPRESS);
        }
        if (clientStats.getMaxShoulderspress() != null) {
            values.put(KEY_MAX_SHOULDERSPRESS, clientStats.getMaxShoulderspress());
        } else {
            values.putNull(KEY_MAX_SHOULDERSPRESS);
        }
        values.put(KEY_DATE_STATS, dateToString(new Date()));

        int clientStatsId = (int) db.insert(TABLE_CLIENT_STATS, null, values);
        clientStats.setId(clientStatsId);

        return clientStats;
    }

    public void updateClientStats(ClientStats clientStats) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STATS_CLIENT_ID, clientStats.getClientId());
        if (clientStats.getWeight() != null) {
            values.put(KEY_WEIGHT_STATS, clientStats.getWeight());
        } else {
            values.putNull(KEY_WEIGHT_STATS);
        }
        if (clientStats.getMaxBackSquat() != null) {
            values.put(KEY_MAX_BACK_SQUAT, clientStats.getMaxBackSquat());
        } else {
            values.putNull(KEY_MAX_BACK_SQUAT);
        }
        if (clientStats.getMaxFrontSquat() != null) {
            values.put(KEY_MAX_FRONT_SQUAT, clientStats.getMaxFrontSquat());
        } else {
            values.putNull(KEY_MAX_FRONT_SQUAT);
        }
        if (clientStats.getMaxDeadlift() != null) {
            values.put(KEY_MAX_DEADLIFT, clientStats.getMaxDeadlift());
        } else {
            values.putNull(KEY_MAX_DEADLIFT);
        }
        if (clientStats.getMaxBenchpress() != null) {
            values.put(KEY_MAX_BENCHPRESS, clientStats.getMaxBenchpress());
        } else {
            values.putNull(KEY_MAX_BENCHPRESS);
        }
        if (clientStats.getMaxShoulderspress() != null) {
            values.put(KEY_MAX_SHOULDERSPRESS, clientStats.getMaxShoulderspress());
        } else {
            values.putNull(KEY_MAX_SHOULDERSPRESS);
        }
        values.put(KEY_DATE_STATS, dateToString(clientStats.getDate()));

        db.update(TABLE_CLIENT_STATS, values, KEY_ID_CLIENT_STATS + " = ? ",
                new String[]{String.valueOf(clientStats.getId())});
    }

    public void deleteClientStats(int clientStatsId) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CLIENT_STATS, KEY_ID_CLIENT_STATS + " = ?",
                new String[]{String.valueOf(clientStatsId)});
    }

    //endregion

    //region Client workout methods

    public List<ClientWorkout> getClientWorkoutForClient(int clientId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENT_WORKOUT + " WHERE " + KEY_CLIENT_WORKOUT_CLIENT_ID + " = " +
                String.valueOf(clientId);

        List<ClientWorkout> results = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                ClientWorkout result = new ClientWorkout();
                result.setId(c.getInt(c.getColumnIndex(KEY_ID_CLIENT_WORKOUT)));
                result.setClientId(c.getInt(c.getColumnIndex(KEY_CLIENT_WORKOUT_CLIENT_ID)));
                result.setDate(parseStringToDate(c.getString(c.getColumnIndex(KEY_DATE_CLIENT_WORKOUT))));
                result.setIsFinished(c.getInt(c.getColumnIndex(KEY_IS_FINISHED)) > 0);

                results.add(result);
            } while (c.moveToNext());
        }

        return results;
    }

    public ClientWorkout createClientWorkout(ClientWorkout clientWorkout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLIENT_WORKOUT_CLIENT_ID, clientWorkout.getClientId());
        values.put(KEY_DATE_CLIENT_WORKOUT, dateToString(clientWorkout.getDate()));
        values.put(KEY_IS_FINISHED, clientWorkout.getIsFinished() ? 1 : 0);

        int clientWorkoutId = (int) db.insert(TABLE_CLIENT_WORKOUT, null, values);
        clientWorkout.setId(clientWorkoutId);

        return clientWorkout;
    }

    public void updateClientWorkout(ClientWorkout clientWorkout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLIENT_WORKOUT_CLIENT_ID, clientWorkout.getClientId());
        values.put(KEY_DATE_CLIENT_WORKOUT, dateToString(clientWorkout.getDate()));
        values.put(KEY_IS_FINISHED, clientWorkout.getIsFinished() ? 1 : 0);

        db.update(TABLE_CLIENT_WORKOUT, values, KEY_ID_CLIENT_WORKOUT + " = ? ",
                new String[]{String.valueOf(clientWorkout.getId())});
    }

    public void deleteClientWorkout(int clientWorkoutId) {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean hasErrors = false;

        db.beginTransaction();

        try {

            //Delete sets for this client workout

            String deleteSetsQuery = "DELETE FROM " + TABLE_SETS + " JOIN " + TABLE_EXERCISES + " ON " +
                    TABLE_SETS + "." + KEY_EXERCISE_ID + " = " + TABLE_EXERCISES + "." + KEY_ID_EXERCISES
                    + " WHERE " + TABLE_EXERCISES + "." + KEY_CLIENT_WORKOUT_ID + " = ?";

            db.rawQuery(deleteSetsQuery, new String[]{String.valueOf(clientWorkoutId)});

            //Delete exercises for this client workout
            db.delete(TABLE_EXERCISES, KEY_CLIENT_WORKOUT_ID + " = ?",
                    new String[]{String.valueOf(clientWorkoutId)});

            //Delete the client workout
            db.delete(TABLE_CLIENT_WORKOUT, KEY_ID_CLIENT_WORKOUT + " = ?",
                    new String[]{String.valueOf(clientWorkoutId)});

        } catch (Exception e) {
            hasErrors = true;
            Log.e(LOG, e.getStackTrace().toString());
        }

        if (!hasErrors) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    //endregion

    //region Exercise methods

    public List<Exercise> getExercisesForWorkout(int clientWorkoutId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_EXERCISES + " WHERE " + KEY_CLIENT_WORKOUT_ID + " = " +
                String.valueOf(clientWorkoutId);

        List<Exercise> results = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Exercise result = new Exercise();
                result.setId(c.getInt(c.getColumnIndex(KEY_ID_EXERCISES)));
                if (!c.isNull(c.getColumnIndex(KEY_EXERCISE_EXERCISES))) {
                    result.setExercise(c.getString(c.getColumnIndex(KEY_EXERCISE_EXERCISES)));
                }
                result.setClientWorkoutId(c.getInt(c.getColumnIndex(KEY_CLIENT_WORKOUT_ID)));
                if (!c.isNull(c.getColumnIndex(KEY_DESCRIPTION))) {
                    result.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                }

                results.add(result);
            } while (c.moveToNext());
        }

        return results;
    }

    public Exercise createExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (!isStringNullOrEmpty(exercise.getExercise())) {
            values.put(KEY_EXERCISE_EXERCISES, exercise.getExercise());
        } else {
            values.putNull(KEY_EXERCISE_EXERCISES);
        }
        values.put(KEY_CLIENT_WORKOUT_ID, exercise.getClientWorkoutId());
        if (!isStringNullOrEmpty(exercise.getDescription())) {
            values.put(KEY_DESCRIPTION, exercise.getDescription());
        } else {
            values.putNull(KEY_DESCRIPTION);
        }

        int exerciseId = (int) db.insert(TABLE_EXERCISES, null, values);
        exercise.setId((exerciseId));

        return exercise;
    }

    public void updateExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (!isStringNullOrEmpty(exercise.getExercise())) {
            values.put(KEY_EXERCISE_EXERCISES, exercise.getExercise());
        } else {
            values.putNull(KEY_EXERCISE_EXERCISES);
        }
        values.put(KEY_CLIENT_WORKOUT_ID, exercise.getClientWorkoutId());
        if (!isStringNullOrEmpty(exercise.getDescription())) {
            values.put(KEY_DESCRIPTION, exercise.getDescription());
        } else {
            values.putNull(KEY_DESCRIPTION);
        }

        db.update(TABLE_EXERCISES, values, KEY_EXERCISE_ID + " = ?",
                new String[]{String.valueOf(exercise.getId())});
    }

    public void deleteExercise(int exerciseId) {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean hasErrors = false;

        db.beginTransaction();

        try {

            //Delete sets for this exercise
            db.delete(TABLE_SETS, KEY_EXERCISE_ID + " = ?", new String[]{String.valueOf(exerciseId)});

            //Delete the exercise
            db.delete(TABLE_EXERCISES, KEY_ID_EXERCISES + " = ?", new String[]{String.valueOf(exerciseId)});

        } catch (Exception e) {
            hasErrors = true;
            Log.e(LOG, e.getStackTrace().toString());
        }

        if (!hasErrors) {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    //endregion

    //region Set methods

    public List<Set> getSetsForExercise(int exerciseId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SETS + " WHERE " + KEY_EXERCISE_ID + " = " +
                String.valueOf(exerciseId);

        List<Set> results = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Set result = new Set();
                result.setId(c.getInt(c.getColumnIndex(KEY_ID_SETS)));
                if (!c.isNull(c.getColumnIndex(KEY_EXERCISE_ID))) {
                    result.setExerciseId(c.getInt(c.getColumnIndex(KEY_EXERCISE_ID)));
                }
                if (!c.isNull(c.getColumnIndex(KEY_EXERCISE_SETS))) {
                    result.setExercise(c.getString(c.getColumnIndex(KEY_EXERCISE_SETS)));
                }
                result.setSets(c.getInt(c.getColumnIndex(KEY_SETS)));
                result.setReps(c.getInt(c.getColumnIndex(KEY_REPS)));
                if (!c.isNull(c.getColumnIndex(KEY_WEIGHT_SETS))) {
                    result.setWeight(c.getInt(c.getColumnIndex(KEY_WEIGHT_SETS)));
                }

                results.add(result);
            } while (c.moveToNext());
        }

        return results;
    }

    public Set createSet(Set set) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (set.getExerciseId() != null) {
            values.put(KEY_EXERCISE_ID, set.getExerciseId());
        } else {
            values.putNull(KEY_EXERCISE_ID);
        }
        if (!isStringNullOrEmpty(set.getExercise())) {
            values.put(KEY_EXERCISE_SETS, set.getExercise());
        } else {
            values.putNull(KEY_EXERCISE_SETS);
        }
        values.put(KEY_SETS, set.getSets());
        values.put(KEY_REPS, set.getReps());
        if (set.getWeight() != null) {
            values.put(KEY_WEIGHT_SETS, set.getWeight());
        } else {
            values.putNull(KEY_WEIGHT_SETS);
        }

        int setId = (int) db.insert(TABLE_SETS, null, values);
        set.setId(setId);

        return set;
    }

    public void updateSet(Set set) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if (set.getExerciseId() != null) {
            values.put(KEY_EXERCISE_ID, set.getExerciseId());
        } else {
            values.putNull(KEY_EXERCISE_ID);
        }
        if (!isStringNullOrEmpty(set.getExercise())) {
            values.put(KEY_EXERCISE_SETS, set.getExercise());
        } else {
            values.putNull(KEY_EXERCISE_SETS);
        }
        values.put(KEY_SETS, set.getSets());
        values.put(KEY_REPS, set.getReps());
        if (set.getWeight() != null) {
            values.put(KEY_WEIGHT_SETS, set.getWeight());
        } else {
            values.putNull(KEY_WEIGHT_SETS);
        }

        db.update(TABLE_SETS, values, KEY_ID_SETS + " = ?",
                new String[]{String.valueOf(set.getId())});
    }

    public void deleteSet(int setId) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SETS, KEY_ID_SETS + " = ?",
                new String[]{String.valueOf(setId)});
    }

    //endregion
}
