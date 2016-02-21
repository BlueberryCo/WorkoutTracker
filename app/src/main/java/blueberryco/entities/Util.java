package blueberryco.entities;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by boiko on 01-Feb-16.
 */
public final class Util {

    private static final String LOG = "Util";

    private Util(){
        throw new AssertionError("Cannot have instance of utility class.");
    }


    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String EMPTY_STRING = "";

    public static Date parseStringToDate(String input){
        DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        try {
            return  format.parse(input);
        } catch (ParseException e) {
            Log.e(LOG, e.getStackTrace().toString());
        }

        return null;
    }

    public static String dateToString(Date input){
        DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        return format.format(input);
    }

    public static boolean isNullOrEmptyString(String input){
        if(input == null || input.equals("null") || input.equals("") || input.trim()
                .length() <= 0){
            return true;
        }

        return false;
    }

    public static String floatToString(Float input){
        return String.valueOf(input);
    }
}
